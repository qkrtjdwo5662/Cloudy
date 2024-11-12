package com.cloudy.domain.member.model.dto.response;

import com.cloudy.domain.member.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(name = "normal 계정 response dto", description = "normal 계정 response dto")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class NormalMemberGetResponse {

    @Schema(description = "department name", example = "developer")
    private String departmentName;

    @Schema(description = "login id", example = "cloudy")
    private String loginId;

    public static NormalMemberGetResponse from(Member member){
        return new NormalMemberGetResponse(member.getDepartmentName(), member.getLoginId());
    }
}
