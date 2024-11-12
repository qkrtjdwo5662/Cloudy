package com.cloudy.domain.member.model;

import com.cloudy.domain.member.model.dto.request.NormalMemberCreateRequest;
import com.cloudy.domain.member.model.dto.request.SuperMemberCreateRequest;
import com.cloudy.global.config.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @NotBlank
    private String departmentName; //부서명

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotNull
    private boolean isUseServiceAlarm; //기본값 false

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank
    @Column(unique = true, length = 20)
    private String businessRegistrationNumber; // 사업자 등록번호

    public Member(String departmentName, String loginId, String password, Role role, String businessRegistrationNumber) {
        this.departmentName = departmentName;
        this.loginId = loginId;
        this.password = password;
        this.role = role;
        this.isUseServiceAlarm = false;
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    public static Member of(NormalMemberCreateRequest memberCreateRequest, String encode) {
        return new Member(memberCreateRequest.getDepartmentName(),
                memberCreateRequest.getLoginId(),
                encode,
                Role.NORMAL,
                memberCreateRequest.getBusinessRegistrationNumber());
    }

    public static Member createSuperMember(SuperMemberCreateRequest memberCreateRequest, String encode) {
        return new Member(memberCreateRequest.getDepartmentName(),
                memberCreateRequest.getLoginId(),
                encode,
                Role.SUPER,
                memberCreateRequest.getBusinessRegistrationNumber());
    }
}
