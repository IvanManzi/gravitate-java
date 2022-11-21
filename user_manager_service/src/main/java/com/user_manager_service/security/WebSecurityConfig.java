package com.user_manager_service.security;


import com.user_manager_service.security.filter.AuthEntryPointJwt;
import com.user_manager_service.security.filter.CustomAuthorizationFilter;
import com.user_manager_service.service.impl.GravitateUserManagerServiceImpl;
import com.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.util.Constants.ADMIN_USER;
import static com.util.Constants.CLIENT_USER;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableGlobalMethodSecurity(
    prePostEnabled = true)
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
      @Autowired
      GravitateUserManagerServiceImpl userDetailsService;

      @Autowired
      private AuthEntryPointJwt unauthorizedHandler;

      @Bean
      public CustomAuthorizationFilter authenticationJwtTokenFilter() {
        return new CustomAuthorizationFilter();
      }

      @Bean
      public DaoAuthenticationProvider authenticationProvider() {
          DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

          authProvider.setUserDetailsService(userDetailsService);
          authProvider.setPasswordEncoder(passwordEncoder());

          return authProvider;
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
          http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
          http.sessionManagement().sessionCreationPolicy(STATELESS);
          http.authorizeRequests().antMatchers("/api/v1/user/login").permitAll();
          http.authorizeRequests().antMatchers("/api/v1/user/password-reset","/api/v1/user/profile","/api/v1/user/security-question/**").hasAnyAuthority(ADMIN_USER,CLIENT_USER);
          http.authorizeRequests().antMatchers("/api/v1/user/**").hasAuthority(ADMIN_USER);
          http.authorizeRequests().anyRequest().authenticated();

          http.authenticationProvider(authenticationProvider());
          http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
          return http.build();
      }
}
