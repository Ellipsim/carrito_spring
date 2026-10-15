package com.carrito.config;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth 
            	.requestMatchers("/", "/index", "/cart/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/products").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers(
                	"/products/add", 
                	"/products/edit/**", 
                	"/products/save", 
                	"/products/delete/**"
                ).hasRole("ADMIN")
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
            )
            
            .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/h2-console/**")
                )
            
            .headers(headers -> headers
                    .frameOptions(frame -> frame.sameOrigin())
                )

            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/products", false)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
            );

        return http.build();
    }
    
    @Bean
    public UserDetailsService users() {

        UserDetails user = User
                .withUsername("user")
                .password("{noop}1234")
                .roles("USER")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}