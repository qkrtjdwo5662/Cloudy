package com.cloudy.domain.alarm.model;

import com.cloudy.domain.member.model.Member;
import com.cloudy.global.config.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alarm")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alramId;

    @Column(length = 100, nullable = false)
    private String serverName;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String content;

    @Column(length = 100, nullable = false)
    private Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Alarm(String serverName, String title, String content, boolean isRead, Member member) {
        this.serverName = serverName;
        this.title = title;
        this.content = content;
        this.isRead = isRead;
        this.member = member;
    }
}
