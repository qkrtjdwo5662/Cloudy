package com.cloudy.global.config.security;

import com.cloudy.global.util.StringArrayListConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.cloudy.domain.member.model.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
public class PrincipalDetails implements UserDetails {

    private Integer userId;
    private Role role;
    private Map<String, Object> attributes;

    /**
     * 일반 로그인 생성자
     */
    public PrincipalDetails(Integer userId, Role role) {
        this.userId = userId;
        this.role = role;
    }

    /**
     * UserDetails 인터페이스 메소드, user의 role 반환
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        List<String> roles = StringArrayListConverter.convertStringToList(role.getRole());
        roles.forEach(r -> collection.add(()->r));
        return collection;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return userId.toString();
    }
}
