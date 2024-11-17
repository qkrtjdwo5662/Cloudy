package com.cloudy.domain.server.model;

import com.cloudy.domain.instance.model.Instance;
import com.cloudy.domain.member.model.Member;
import com.cloudy.global.config.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "server")
@Getter
@NoArgsConstructor
public class Server extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serverId;

    @Column(length = 100, nullable = false)
    private String serverName; // 서버 이름

    @Column(length = 100, nullable = false)
    private String paymentType; // 결제 방식

    @Column(length = 100, nullable = false)
    private int serverLimit; // 임계치

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 멤버 객체 참조

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instance_id", nullable = false)
    private Instance instance; // 인스턴스 객체 참조

    // 생성자
    public Server(String serverName, int serverLimit, String paymentType, Member member, Instance instance) {
        this.serverName = serverName;
        this.paymentType = paymentType;
        this.serverLimit = serverLimit;
        this.member = member;
        this.instance = instance;
    }

    // 서버 이름 업데이트 메서드
    public void updateName(String newName) {
        this.serverName = newName;
    }

    public void setServerLimit(int limitValue) {
        this.serverLimit = limitValue;
    }
}
