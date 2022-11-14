package com.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
      private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

      public static String generateJwtToken(Authentication authentication) throws ParseException {

          UserDetailsService userPrincipal = (UserDetailsService) authentication.getPrincipal();
          Algorithm algorithm = Algorithm.HMAC256(Constants.jwtSecret.getBytes());
          String access_token = JWT.create()
                  .withSubject(userPrincipal.getUsername())
                  .withKeyId(userPrincipal.getUserId().toString())
                  .withExpiresAt(DateUtil.addYearsToCurrentDate(Constants.jwtExpirationHrs))
                  .withClaim("roles",userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                  .sign(algorithm);
          return access_token;
      }

    /**
     * Get username from JWT token time 2022-04-14 14:20:11
     *
     * @Param HttpServletRequest, HttpServletResponse
     * @return String
     */

    public static String getUserNameFromToken(String token) throws IOException {
        Algorithm algorithm = Algorithm.HMAC256(Constants.jwtSecret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        return username;
    }


    /**
     * Get userId from JWT token time 2022-04-14 14:20:11
     *
     * @Param token
     * @return String
     */

    public static String getUserIdFromJwtToken(String token) throws IOException {
        Algorithm algorithm = Algorithm.HMAC256(Constants.jwtSecret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String userId = decodedJWT.getKeyId();
        return userId;
    }

    /**
     * Verify JWT token time 2022-04-14 14:20:11
     *
     * @return String
     * @Param token
     */

    public static DecodedJWT verifyJwtToken(String token) throws IOException {
        Algorithm algorithm = Algorithm.HMAC256(Constants.jwtSecret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

}
