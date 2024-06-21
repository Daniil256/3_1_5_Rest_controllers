package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final SuccessUserHandler successUserHandler;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler handler) {
        this.successUserHandler = handler;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/").not().fullyAuthenticated()
                        .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/user").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/**","/script/*", "/styles/*", "/pages/*").permitAll()
                        .anyRequest().permitAll())
                .formLogin(e -> e
                        .loginPage("/")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginProcessingUrl("/perform-login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/")
                        .successHandler(successUserHandler))
                .logout(e -> e.logoutSuccessUrl("/").logoutUrl("/logout"))
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.accessDeniedPage("/400"));
        return http.build();
    }
}
