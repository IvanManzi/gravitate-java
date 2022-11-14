package com.user_manager_service.security.filter;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user_manager_service.service.impl.GravitateUserManagerServiceImpl;
import com.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

      @Autowired
      private GravitateUserManagerServiceImpl userDetailsService;

      private static final Logger logger = LoggerFactory.getLogger(CustomAuthorizationFilter.class);

      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
              throws IOException, ServletException {
          if(request.getServletPath().equals("/api/v1/user/login")){
              filterChain.doFilter(request,response);
          }else{
              String authorizationHeader = request.getHeader(AUTHORIZATION);
              if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                  try {
                      String token = authorizationHeader.substring("Bearer ".length());
                      DecodedJWT decodedJWT = JwtUtils.verifyJwtToken(token);
                      String username = decodedJWT.getSubject();
                      String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                      Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                      stream(roles).forEach(role -> {
                          authorities.add(new SimpleGrantedAuthority(role));
                      });
                      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                      filterChain.doFilter(request, response);
                  } catch (Exception exception) {
                      response.setStatus(FORBIDDEN.value());
                      Map<String, String> error = new HashMap<>();
                      error.put("error_message", exception.getMessage());
                      response.setContentType(APPLICATION_JSON_VALUE);
                      new ObjectMapper().writeValue(response.getOutputStream(), error);
                  }
              } else {
                  response.setStatus(FORBIDDEN.value());
                  Map<String, String> error = new HashMap<>();
                  error.put("error", "Forbidden to access this resource. ");
                  response.setContentType(APPLICATION_JSON_VALUE);
                  new ObjectMapper().writeValue(response.getOutputStream(), error);
              }
          }

      }
}
