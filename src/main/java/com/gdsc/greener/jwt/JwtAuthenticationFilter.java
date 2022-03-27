package com.gdsc.greener.jwt;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gdsc.greener.response.ErrorResponse;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.io.DecodingException;
//import io.jsonwebtoken.security.SignatureException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends GenericFilterBean {
//
//    private final JwtTokenProvider jwtTokenProvider;
//    private final ObjectMapper objectMapper;
//
//    @Override
//    // request로 들어오는 Jwt의 유효성 검증 - JwtTokenProvider.validationToken()을 필터로서 FilterChain 추가
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        try {
//            Claims claims = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//            if (claims != null)
//                SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(claims));
//            filterChain.doFilter(request, response);
//        } catch (SignatureException e) {
//            sendErrorMessage((HttpServletResponse) response, "0003", "유효하지 않은 토큰입니다.");
//        } catch (MalformedJwtException e) {
//            sendErrorMessage((HttpServletResponse) response, "0004", "손상된 토큰입니다.");
//        } catch (DecodingException e) {
//            sendErrorMessage((HttpServletResponse) response, "0005", "잘못된 인증입니다.");
//        } catch (ExpiredJwtException e) {
//            sendErrorMessage((HttpServletResponse) response, "0006", "만료된 토큰입니다.");
//        }
//    }
//
//    private void sendErrorMessage(HttpServletResponse res, String error, String message) throws IOException {
//        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        res.setContentType(MediaType.APPLICATION_JSON.toString());
//        res.getWriter().write(this.objectMapper.writeValueAsString(new ErrorResponse(HttpStatus.FORBIDDEN, error, message)));
//    }
//}

import com.gdsc.greener.domain.Account;
import com.gdsc.greener.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = getAuthentication(request);

        if (authentication != null) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null) {
            return null;
        }

        String token = authorizationHeader.substring("Bearer ".length());

        Claims claims;
        claims = jwtUtil.getClaims(token);

        Set<GrantedAuthority> roles = new HashSet<>();
        String role = (String) claims.get("role");
        roles.add(new SimpleGrantedAuthority("ROLE_" + role));

        return new UsernamePasswordAuthenticationToken(new Account(claims), null, roles);
    }

}