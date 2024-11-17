package com.cloudy.global.config;

import com.cloudy.global.config.security.ExceptionHandlerFilter;
import com.cloudy.global.config.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    // ⭐️ CORS 설정
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 허용할 헤더 설정
        config.setAllowedHeaders(Arrays.asList(
                "Authorization",
                "Content-Type",
                "X-Requested-With",
                "Accept",
                "Origin"
        ));

        // 허용할 HTTP 메서드 설정
        config.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        // 허용할 Origin (명시적으로 정의)
        config.setAllowedOrigins(Arrays.asList(
                "http://k11a606.p.ssafy.io:3000",
                "http://localhost:3000",
                "https://i11a607.p.ssafy.io",
                "http://10.0.2.2:3000",
                "https://mono-repo-practice.vercel.app"
        ));

        // 인증 정보 포함 허용
//        config.setAllowCredentials(true);

        // 캐시 설정 (선택 사항, 필요하면 추가)
        config.setMaxAge(3600L);

        // 경로에 대해 CORS 설정 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // Password 인코딩 방식에 BCrypt 암호화 방식 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // security를 적용하지 않을 리소스
        return web -> web.ignoring()
                // error endpoint를 열어줘야 함, favicon.ico 추가!
                .requestMatchers("/error", "/favicon.ico","/swagger-ui/**","/swagger-resources/**","/v3/api-docs/**",
                        "/swagger-ui.html/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 기능 비활성화, 세션을 사용하지 않기때문에
                .csrf((csrfConfig) ->
                        csrfConfig.disable()
                )
                // Clickjacking 공격을 방지하기 위한 X-Frame-Options 헤더 비활성화
                // 캐시 제어 비활성화
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                        frameOptionsConfig.disable()
                                )
                                .cacheControl(cacheControl -> cacheControl.disable())

                )
                // session 사용 X 명시
                .sessionManagement((sessionConfig) ->
                        sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .cors(corsConfig ->
                        corsConfig.configurationSource(corsConfigurationSource()))

                // HTTP 요청에 대한 보안 설정
                // todo: 보안 끄면 api 정상실행
                // todo: roll 부여의 의문점
                .authorizeHttpRequests(authorizeRequests ->
                                authorizeRequests
                                        // '/login', '/oauth2' 나머지 모든 요청은 인증된 사용자만 접근 가능
                                        .requestMatchers("/auth/**"
                                                ,"/error", "login/oauth2/code/kakao","/swagger-ui/**"
                                                ,"/swagger-resources/**", "/v3/api-docs/**").permitAll()
//                                        .requestMatchers("/**").permitAll()
//                                        .requestMatchers("/admin").hasRole("ADMIN") // admin은 ADMIN 롤만
                                        .requestMatchers("/alarms/subscribe").permitAll()
                                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class);

        return http.build();
    }
}
