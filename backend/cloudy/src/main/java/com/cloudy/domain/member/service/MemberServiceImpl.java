package com.cloudy.domain.member.service;

import com.cloudy.domain.member.model.Member;
import com.cloudy.domain.member.model.Role;
import com.cloudy.domain.member.model.dto.response.NormalMemberGetResponse;
import com.cloudy.domain.member.model.dto.response.NormalMemberGetResponses;
import com.cloudy.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
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
}
