package com.gdsc.greener.config.auth;

import com.gdsc.greener.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션 disable
                .and()
                .authorizeRequests()// URL별 권한 권리
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/v1/**").permitAll() // /api/v1/** 은 USER권한만 접근 가능
                .anyRequest().authenticated() // anyRequest : 설정된 값들 이외 나머지 URL 나타냄, authenticated : 인증된 사용자
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint() // oauth2 로그인 성공 후 가져올 때의 설정들
                // 소셜로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체 등록
                .userService(userService); // 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시

        super.configure(http);
    }
}
*/

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**","/js/**","/Semantic-UI-master/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/v1/admin/contents").permitAll()
                .and().formLogin().permitAll() // 로그인관련 설정을 진행합니다.
                .loginPage("/signin")
                .usernameParameter("userID")
                .passwordParameter("password")
                .loginProcessingUrl("/signin")
                .defaultSuccessUrl("/resultSignin")
                .and().logout()
                .logoutUrl("/signout")
                .logoutSuccessUrl("/signin")
                .permitAll()
                .and().csrf().ignoringAntMatchers("/userIdCheck","/searchSchool", "/questionList","/myQuestionList","/infoStudent/**","/student/interview/question/record","/student/interview/question/stop");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}