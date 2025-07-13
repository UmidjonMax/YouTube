package dasturlash.uz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // csrf ni o'chiramiz
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/attach/**").permitAll() // faqat attach endpointlariga ruxsat
                        .anyRequest().permitAll() // hamma endpointga ruxsat beramiz (shart emas)
                );

        return http.build();
    }
}
