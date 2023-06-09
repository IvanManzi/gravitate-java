package com.content_manager_service.security;


import com.content_manager_service.security.filter.CustomAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.util.Constants.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {

    @Bean
    public CustomAuthorizationFilter authenticationJwtTokenFilter() {
        return new CustomAuthorizationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/v1/content/blog/**",
                                                        "/api/v1/content/performance-evaluation/{userId}/points",
                                                        "/api/v1/content/task/**",
                                                        "/api/v1/content/position/all",
                                                        "/api/v1/content/position/referral/create",
                                                        "/api/v1/content/position/referral/all",
                                                        "/api/v1/content/position/self/referral/create",
                                                        "/api/v1/content/position/self/referral/all",
                                                        "/api/v1/content/client-referral/create",
                                                        "/api/v1/content/project/",
                                                        "/api/v1/content/forum/**",
                                                        "/api/v1/content/wish/all",
                                                        "/api/v1/content/policy/all",
                                                        "/api/v1/content/wish/comment").hasAnyAuthority(NON_ADMIN,ADMIN_USER, PROJECT_MANAGER);

        http.authorizeRequests().antMatchers("/api/v1/content/performance-evaluation/**",
                "/api/v1/content/blog/{blogId}/isAwarded/{status}",
                "/api/v1/content/forum/solution/{solutionId}/isAwarded/{status}").hasAnyAuthority(ADMIN_USER, PROJECT_MANAGER);
        http.authorizeRequests().antMatchers("/api/v1/content/**").hasAuthority(ADMIN_USER);
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
