package com.user_manager_service.security.filter;


import com.user_manager_service.service.impl.GravitateUserManagerServiceImpl;
import com.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

      @Autowired
      private GravitateUserManagerServiceImpl userDetailsService;

      private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
          try {
            String jwt = parseJwt(request);
            if (jwt != null && JwtUtils.validateJwtToken(jwt)) {
              String username = JwtUtils.getUserNameFromJwtToken(jwt);
              log.info("{}",username);
              UserDetails userDetails = userDetailsService.loadUserByUsername(username);
              UsernamePasswordAuthenticationToken authentication =
                  new UsernamePasswordAuthenticationToken(
                      userDetails,
                      null,
                      userDetails.getAuthorities());
              log.info("{}",userDetails);
              authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

              SecurityContextHolder.getContext().setAuthentication(authentication);
            }
          } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
          }

          filterChain.doFilter(request, response);
      }

      private String parseJwt(HttpServletRequest request) {
          String headerAuth = request.getHeader("Authorization");

          if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
          }

          return null;
      }
}
