package community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public WebSecurityCustomizer configure() {      // 스프링 시큐리티 기능 비활성화
        return web -> web.ignoring()
//                .requestMatchers(toH2Console())
                .requestMatchers("/static/**", "/vendor/**", "/css/**", "/img/**", "/js/**");
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth ->              // 인증, 인가 설정
                        auth.requestMatchers(HttpMethod.GET, "/article/post").hasAnyAuthority("USER","ADMIN")

                                .requestMatchers(HttpMethod.POST, "/create","/api/articles").hasAnyAuthority("USER","ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/update").hasAnyAuthority("USER","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/articles/{id}").hasAnyAuthority("USER","ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/delete","/api/articles/delete/{id}").hasAnyAuthority("USER","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/login", "/signup", "/", "/article/**", "/articles/{type}","/error").permitAll()
                                .requestMatchers(HttpMethod.POST, "/user","/checkEmail","/checkNickName").permitAll()
                                .requestMatchers(HttpMethod.GET, "/admin").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(auth -> auth.loginPage("/login")     // 폼 기반 로그인 설정
                        .defaultSuccessUrl("/"))
                .logout(auth -> auth.logoutUrl("/logout").logoutSuccessUrl("/") // 로그아웃 설정
                        .invalidateHttpSession(true))
                .csrf(AbstractHttpConfigurer::disable)// csrf 비활성화
                .sessionManagement(session -> session.maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/login"));

        return httpSecurity.build();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
