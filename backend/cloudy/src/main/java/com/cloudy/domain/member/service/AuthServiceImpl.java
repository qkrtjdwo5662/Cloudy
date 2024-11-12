package com.cloudy.domain.member.service;

import com.cloudy.domain.member.exception.LoginFailException;
import com.cloudy.domain.member.exception.NormalRegisterFailException;
import com.cloudy.domain.member.exception.ReissueFailException;
import com.cloudy.domain.member.model.Member;
import com.cloudy.domain.member.model.dto.request.MemberLoginRequest;
import com.cloudy.domain.member.model.dto.request.NomalMemberCreateRequest;
import com.cloudy.domain.member.model.dto.request.SuperMemberCreateRequest;
import com.cloudy.domain.member.model.dto.request.MemberReissueRequest;
import com.cloudy.domain.member.model.dto.response.MemberLoginResponse;
import com.cloudy.domain.member.model.dto.response.MemberLoginResponseOriginal;
import com.cloudy.domain.member.model.dto.response.MemberReissueTokenResponse;
import com.cloudy.domain.member.repository.MemberRepository;
import com.cloudy.domain.server.model.Server;
import com.cloudy.domain.server.repository.ServerRepository;
import com.cloudy.global.config.security.jwt.JwtToken;
import com.cloudy.global.config.security.jwt.JwtTokenProvider;
import com.cloudy.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ServerRepository serverRepository;

    /*
     * super 계정 회원가입.
     * */
    @Override
    public void superRegister(SuperMemberCreateRequest memberCreateRequest) {
        if(memberRepository.existsByLoginId(memberCreateRequest.getLoginId())){
            throw new NormalRegisterFailException(ErrorCode.DUPLICATED_MEMBER, "해당 ID를 가진 회원이 이미 존재합니다.");
        }

        // 회원 등록
        Member member = Member.createSuperMember(memberCreateRequest, passwordEncoder.encode(memberCreateRequest.getPassword()));

        memberRepository.save(member);
    }

    /*
    * normal 계정 회원가입.
    * */
    @Override
    public void normalRegister(NomalMemberCreateRequest memberCreateRequest) {
        if(memberRepository.existsByLoginId(memberCreateRequest.getLoginId())){
            throw new NormalRegisterFailException(ErrorCode.DUPLICATED_MEMBER, "해당 ID를 가진 회원이 이미 존재합니다.");
        }

        // 회원 등록
        Member member = Member.of(memberCreateRequest, passwordEncoder.encode(memberCreateRequest.getPassword()));

        memberRepository.save(member);
    }

    @Override
    public boolean checkDuplicatedId(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

    @Override
    public MemberLoginResponse login(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findByLoginId(memberLoginRequest.getLoginId())
                .orElseThrow(()->new LoginFailException(ErrorCode.NOT_EXIST_MEMBER,"해당하는 계정이 존재하지 않습니다."));

        if (!passwordEncoder.matches(memberLoginRequest.getPassword(), member.getPassword())) {
            throw new LoginFailException(ErrorCode.NOT_MATCH_PASSWORD, "아이디와 비밀번호 정보가 일치하지 않습니다.");
        }

        // JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.issue(member.getMemberId(), member.getRole());
        List<Server> serverList = serverRepository.findByMember_MemberIdOrderByServerId(member.getMemberId());

        Server server = null;
        if(!serverList.isEmpty()){
            server = serverList.getFirst();
            return MemberLoginResponse.from(jwtToken, server);
        }else{
            return MemberLoginResponse.of(jwtToken.getAccessToken(), jwtToken.getRefreshToken(), null, null);
        }

    }

    @Override
    public MemberLoginResponseOriginal loginOriginal(MemberLoginRequest request) {
        Member member = memberRepository.findByLoginId(request.getLoginId())
                .orElseThrow(()->new LoginFailException(ErrorCode.NOT_EXIST_MEMBER,"해당하는 계정이 존재하지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new LoginFailException(ErrorCode.NOT_MATCH_PASSWORD, "아이디와 비밀번호 정보가 일치하지 않습니다.");
        }

        // JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.issue(member.getMemberId(), member.getRole());

        return MemberLoginResponseOriginal.from(jwtToken);
    }


    @Override
    public MemberReissueTokenResponse reissueToken(MemberReissueRequest memberReissueRequest) {
        if(!jwtTokenProvider.validateToken(memberReissueRequest.getRefreshToken())){
            throw new ReissueFailException(ErrorCode.EXPIRED_REFRESH_TOKEN, "refresh token이 만료되었습니다. 로그인을 해야합니다.");
        }

        return MemberReissueTokenResponse.from(jwtTokenProvider.reissue(memberReissueRequest.getAccessToken()));
    }

    @Override
    public boolean checkBusinessNumberDuplicate(String businessRegistrationNumber) {
        return memberRepository.existsBybusinessRegistrationNumber(businessRegistrationNumber);
    }
}
