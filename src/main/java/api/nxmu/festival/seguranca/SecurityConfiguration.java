package api.nxmu.festival.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints (no authentication required)
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers(whiteList()).permitAll()

                        // Endpoints under /api/evento/auth/** require USER or MASTER role
                        .requestMatchers("/api/evento/auth/**").hasAnyRole("USER", "MASTER")
                        .requestMatchers("/api/apresentacoes").hasAnyRole("USER", "MASTER")
                        .requestMatchers("/api/apresentacoesfiltro").hasAnyRole("USER", "MASTER")
                        .requestMatchers("/api/categorias").hasAnyRole("USER", "MASTER")
                        .requestMatchers("/evento/auth/perfil").hasAnyRole("USER", "MASTER")
                        

                        // All other endpoints require MASTER role
                        .anyRequest().hasRole("MASTER")
                )
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Lista para endpoints publicos
    private String[] whiteList() {
        return new String[]{
                "/api/evento/auth/**", 
                "/api/apresentacao/musica/**",
                "/api/categoriasativa", 
                "/inscricoes"
        };
    }
}