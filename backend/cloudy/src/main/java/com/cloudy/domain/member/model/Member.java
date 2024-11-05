package com.cloudy.domain.member.model;

import com.cloudy.domain.company.model.Company;
import com.cloudy.domain.member.model.dto.request.MemberCreateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
public class Member {

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
    private boolean isUseEmailAlarm; //기본값 false

    @NotNull
    private boolean isUseServiceAlarm; //기본값 false

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Member(String departmentName, String loginId, String password, Role role, Company company) {
        this.departmentName = departmentName;
        this.loginId = loginId;
        this.password = password;
        this.role = role;
        this.company = company;
        this.isUseEmailAlarm = false;
        this.isUseServiceAlarm = false;
    }

    public static Member of(MemberCreateRequest memberCreateRequest, String encode, Company company) {
        return new Member(memberCreateRequest.getDepartmentName(),
                memberCreateRequest.getLoginId(),
                encode,
                Role.NORMAL,
                company);
    }

    public static Member createSuperMember(MemberCreateRequest memberCreateRequest, String encode, Company company) {
        return new Member(memberCreateRequest.getDepartmentName(),
                memberCreateRequest.getLoginId(),
                encode,
                Role.SUPER,
                company);
    }
}
