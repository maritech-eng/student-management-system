package com.example.studentmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Session-based authentication (simplest, most reliable option for a beginner
 * project). Spring Security stores the authenticated user in the HTTP session
 * (JSESSIONID cookie). The frontend just needs to send requests with
 * credentials: 'include' (see static/js/api.js).
 *
 * CSRF is disabled for /api/** to keep the fetch()-based frontend simple,
 * which is a common, accepted simplification for same-origin student/demo
 * projects. If you deploy this publicly, re-enable CSRF protection.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService uds, PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(uds);
        provider.setPasswordEncoder(encoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .exceptionHandling(eh -> eh.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
            .authorizeHttpRequests(auth -> auth
                // Public
                .requestMatchers("/", "/index.html", "/login.html", "/css/**", "/js/**",
                        "/images/**", "/webjars/**", "/favicon.ico", "/uploads/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()

                // Everyone logged in can read most modules (student/staff portal views)
                .requestMatchers(HttpMethod.GET, "/api/**").authenticated()

                // Write access: core admin data
                .requestMatchers(HttpMethod.POST, "/api/students/**", "/api/staff/**", "/api/faculty/**",
                        "/api/departments/**", "/api/courses/**", "/api/subjects/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/students/**", "/api/staff/**", "/api/faculty/**",
                        "/api/departments/**", "/api/courses/**", "/api/subjects/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/students/**", "/api/staff/**", "/api/faculty/**",
                        "/api/departments/**", "/api/courses/**", "/api/subjects/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN")

                // Attendance: Admin/Staff/Faculty can write
                .requestMatchers(HttpMethod.POST, "/api/attendance/**", "/api/notices/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF", "FACULTY")
                .requestMatchers(HttpMethod.PUT, "/api/attendance/**", "/api/notices/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF", "FACULTY")
                .requestMatchers(HttpMethod.DELETE, "/api/attendance/**", "/api/notices/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF", "FACULTY")

                // Fees: Admin/Staff
                .requestMatchers(HttpMethod.POST, "/api/fees/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF")
                .requestMatchers(HttpMethod.PUT, "/api/fees/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF")
                .requestMatchers(HttpMethod.DELETE, "/api/fees/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF")

                // Exams/Results/Timetable: Admin/Faculty
                .requestMatchers(HttpMethod.POST, "/api/exams/**", "/api/results/**", "/api/timetable/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "FACULTY")
                .requestMatchers(HttpMethod.PUT, "/api/exams/**", "/api/results/**", "/api/timetable/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "FACULTY")
                .requestMatchers(HttpMethod.DELETE, "/api/exams/**", "/api/results/**", "/api/timetable/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "FACULTY")

                // Leave: any authenticated user can create their own leave request; approve/reject restricted
                .requestMatchers(HttpMethod.POST, "/api/leaves/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/leaves/*/approve", "/api/leaves/*/reject")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF", "FACULTY")

                // Library/Hostel/Transport/Events/Placements: Admin/Staff manage
                .requestMatchers(HttpMethod.POST, "/api/library/**", "/api/hostel/**", "/api/transport/**",
                        "/api/events/**", "/api/placements/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF")
                .requestMatchers(HttpMethod.PUT, "/api/library/**", "/api/hostel/**", "/api/transport/**",
                        "/api/events/**", "/api/placements/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF")
                .requestMatchers(HttpMethod.DELETE, "/api/library/**", "/api/hostel/**", "/api/transport/**",
                        "/api/events/**", "/api/placements/**")
                    .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF")

                // Anything else under /api requires login
                .requestMatchers("/api/**").authenticated()

                .anyRequest().permitAll()
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("http://localhost:*", "http://127.0.0.1:*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
