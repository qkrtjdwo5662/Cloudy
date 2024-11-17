package com.cloudy.domain.member.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Schema(name = "normal 계정 response dto", description = "normal 계정 response dto")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class NormalMemberGetResponses {
    @Schema(description = "일반회원 목록 조회")
    private List<NormalMemberGetResponse> normalMemberGetResponses;

    public static NormalMemberGetResponses from(List<NormalMemberGetResponse> normalMemberGetResponses){
        return new NormalMemberGetResponses(normalMemberGetResponses);
    }

}
