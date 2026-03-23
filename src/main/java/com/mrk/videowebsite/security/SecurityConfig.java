package com.mrk.videowebsite.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        // Public
                        .requestMatchers("/api/users/login", "/api/users/register").permitAll()
                        .requestMatchers(POST, "/api/contacts/**").permitAll()
                        .requestMatchers(GET, "/api/videos/**").permitAll()

                        .requestMatchers("/api/users/me").authenticated()

                        // USER + ADMIN
                        //.requestMatchers(GET, "/api/videos/**").hasAnyAuthority("USER", "ADMIN")

                        // ADMIN only
                        .requestMatchers(POST, "/api/videos").hasAuthority("ADMIN")
                        .requestMatchers(PUT, "/api/videos/**").hasAuthority("ADMIN")
                        .requestMatchers(DELETE, "/api/videos/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/dashboard/**").hasAuthority("ADMIN")

                        .requestMatchers(GET, "/api/contacts").hasAuthority("ADMIN")
                        .requestMatchers(DELETE, "/api/contacts/**").hasAuthority("ADMIN")

                        .requestMatchers("/api/dashboard/**").hasAuthority("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
