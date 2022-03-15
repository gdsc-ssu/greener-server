package com.gdsc.greener.jwt;

import com.gdsc.greener.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

/**
 * JWT 토큰 처리기
 */
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    private Key key;
    private final long accessTokenValidMilSecond = 1000L * 60 * 30; // 유효시간 : 30분
    private final long refreshTokenValidMilSecond = 1000L * 60 * 60 * 24 * 14; // 유효기간 : 2주

    /**
     * SecretKey 암호화 하면서 초기화
     */
    @PostConstruct
    protected void init() {
        this.key = Keys.hmacShaKeyFor(this.secretKey.getBytes());
    }

    /**
     * AccessToken 생성
     *
     * @param userId 발급할 사용자의 아이디
     * @param role  사용자에게 허용할 권한
     * @return AccessToken
     */
    public String createAccessToken(String userId, Role role) {
        return generateToken(userId, role, accessTokenValidMilSecond);
    }

    /**
     * RefreshToken 생성
     *
     * @param userId 발급할 사용자의 아이디
     * @param role  사용자에게 허용할 권한
     * @return AccessToken
     */
    public String createRefreshToken(String userId, Role role) {
        return generateToken(userId, role, refreshTokenValidMilSecond);
    }

    /**
     * JWTToken 생성
     *
     * @param email              발급할 사용자의 아이디
     * @param role               사용자에게 허용할 권한
     * @param tokenValidMilSecond 토큰 유효시간
     * @return AccessToken
     */
    protected String generateToken(String email, Role role, long tokenValidMilSecond) {
        Date now = new Date();
        List<Role> roles = Collections.singletonList(role);

        return Jwts.builder()
                .claim("userId",email)
                .claim("roles",roles)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilSecond))
                .signWith(this.key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Http Request 에서 JWT 토큰의 데이터 추출
     * Authorization 헤더에 Bearer [Token] 형태로 되어야 함
     *
     * @param req Http 요청
     * @return 토큰 데이터
     */
    public Claims resolveToken(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        if (token == null)
            return null;
        else if (token.contains("Bearer"))
            token = token.replace("Bearer ", "");
        else
            throw new DecodingException("");

        return getClaimsFromToken(token);
    }

    /**
     * 토큰에서 토큰 데이터를 추출
     *
     * @param token JWT 토큰
     * @return 토큰 데이터
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Spring Security 인증토큰 발급
     * accessToken 은 주기가 짧기 때문에 검사없이 허용한다.
     * 매번 DB에 검증 하기엔 OverHead 가 너무 큼
     *
     * @param claims JWT 토큰 데이터
     * @return Spring Security 인증토큰
     */
    public Authentication getAuthentication(Claims claims) {
        return new UsernamePasswordAuthenticationToken(this.getUserId(claims), "", getAuthorities(claims));
    }

    /**
     * JWT 토큰 데이터 에서 UserID 추출
     *
     * @param claims JWT 토큰 데이터
     * @return UserId
     */
    public String getUserId(Claims claims) {
        return claims.getSubject();
    }

    /**
     * JWT 토큰 데이터 Roles 추출
     *
     * @param claims JWT 토큰 데이터
     * @return 사용자 권한 정보
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Claims claims) {
        List<String> roles = claims.get("roles", List.class);
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}