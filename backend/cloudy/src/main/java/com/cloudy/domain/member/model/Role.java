package com.cloudy.domain.member.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum Role {
    NORMAL("ROLE_NORMAL"),
    SUPER("ROLE_SUPER,ROLE_NORMAL");

    private static final Map<String, Role> roleMap = Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(Role::getRole, Function.identity())));

    private final String role;

    public static Role getRole(String role) {
        return roleMap.get(role);
    }
}