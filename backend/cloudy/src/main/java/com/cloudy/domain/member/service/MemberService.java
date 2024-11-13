package com.cloudy.domain.member.service;

import com.cloudy.domain.member.model.dto.response.NormalMemberGetResponses;

public interface MemberService {
    NormalMemberGetResponses getNormalMembers(Long memberId);

    void deleteNormalMember(Long sueprMemberId, Long nomalMemberId);
}
