package com.typefigth.credentials_api.infrastructure.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typefigth.credentials_api.infrastructure.utils.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(Constants.HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(Constants.PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(Constants.PREFIX_TOKEN, "");

        try {
            Claims claims = Jwts.parser().verifyWith(Constants.SECRET_KEY).build().parseSignedClaims(token).getPayload();
            Object uid = claims.get("uid");
//            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
//                    new ObjectMapper()
//                            .addMixIn(SimpleGrantedAuthority.class,
//                                    SimpleGrantedAuthorityJsonCreator.class)
//                            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class)
//            );

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(uid, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (JwtException e) {
            Map<String, String> body = new HashMap<>();
            body.put(Constants.ERROR, e.getMessage());
            body.put(Constants.STATUS, "token not valid");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(Constants.CONTENT_TYPE);
        }
    }

}
