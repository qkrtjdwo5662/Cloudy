package com.cloudy.domain.member.model;

import jakarta.persistence.*;
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

    private String departmentName; //부서명

    private String loginId;

    private String password;

    private String role; //todo: enum으로 수정 필요

}
