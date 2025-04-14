package pos_microservice_api_gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pos_microservice_api_gateway.model.Role;
import pos_microservice_api_gateway.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(
                AuthenticationManagerBuilder.class
        );
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);

        AuthenticationManager authenticationManager = auth.build();

        http.csrf(csrf -> csrf.disable());
        http.authenticationManager(authenticationManager);
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/gateway/inmuebles").permitAll()
                                .requestMatchers("/gateway/inmueble/**").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.GET,"/gateway/compras").permitAll()
                                .requestMatchers("/gateway/compra/**").hasRole(Role.ADMIN.name())
                                .requestMatchers("/api/**").authenticated()
        );


        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /*
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }*/


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return new JwtAuthorizationFilter();
    }
}
