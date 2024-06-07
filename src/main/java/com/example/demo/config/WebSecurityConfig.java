package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(a -> a
                        .requestMatchers("/registration", "/login").not().fullyAuthenticated()
                        .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/user").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/", "/styles/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(e -> e.loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginProcessingUrl("/perform-login")
                        .defaultSuccessUrl("/")
                        .successHandler(successUserHandler))
                .logout(e -> e.logoutSuccessUrl("/").logoutUrl("/logout"))
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.accessDeniedPage("/403"));
        return http.build();
    }
}
