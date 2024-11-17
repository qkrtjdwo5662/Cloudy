package com.cloudy.domain.member.service;

import com.cloudy.domain.member.model.Member;
import com.cloudy.domain.member.model.Role;
import com.cloudy.domain.member.model.dto.response.NormalMemberGetResponse;
import com.cloudy.domain.member.model.dto.response.NormalMemberGetResponses;
import com.cloudy.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public NormalMemberGetResponses getNormalMembers(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        List<NormalMemberGetResponse> responses = memberRepository.findMembersByBusinessRegistrationNumberAndRole(member.getBusinessRegistrationNumber(), Role.valueOf("NORMAL"))
                .stream().map(NormalMemberGetResponse::from).toList();

        return NormalMemberGetResponses.from(responses);
    }

    @Override
    public void deleteNormalMember(Long superMemberId, Long normalMemberId) {
        // SUPER 유저 확인
        Member superMember = memberRepository.findById(superMemberId)
                .orElseThrow(() -> new IllegalArgumentException("SUPER 유저 ID가 유효하지 않습니다."));

        if (!superMember.getRole().equals(Role.SUPER)) {
            throw new IllegalArgumentException("SUPER 유저만 회원 삭제 권한이 있습니다.");
        }

        // 삭제할 일반 유저 확인
        Member normalMember = memberRepository.findById(normalMemberId)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 회원 ID가 유효하지 않습니다."));

        if (!normalMember.getRole().equals(Role.NORMAL)) {
            throw new IllegalArgumentException("삭제 대상은 일반 회원이어야 합니다.");
        }

        // 일반 회원 삭제
        memberRepository.delete(normalMember);
    }

}
