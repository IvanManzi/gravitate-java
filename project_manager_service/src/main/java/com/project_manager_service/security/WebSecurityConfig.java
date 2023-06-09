package com.project_manager_service.security;


import com.project_manager_service.security.filter.CustomAuthorizationFilter;
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
        http.authorizeRequests().antMatchers("/api/v1/project/task/**",
                "/api/v1/project/incentive/user",
                "/api/v1/project/user/billing-info",
                "/api/v1/project/{projectId}/phase/{phaseId}",
                "/api/v1/project/jira/**",
                "/api/v1/project/{projectId}/mark-favorite/{status}",
                "/api/v1/project/all").hasAnyAuthority(NON_ADMIN,ADMIN_USER, PROJECT_MANAGER);
        http.authorizeRequests().antMatchers("/api/v1/project/**").hasAuthority(ADMIN_USER);
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
