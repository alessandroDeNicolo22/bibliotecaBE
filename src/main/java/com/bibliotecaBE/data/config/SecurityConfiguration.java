package com.bibliotecaBE.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    FiltroAutenticazioneJwt filtroAutenticazioneJwt;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests()
                .antMatchers("/auth/**")
                .permitAll()
                .antMatchers("/fornitore/**", "/fatturapassiva/**", "/ordineacquisto/**")
                .hasAnyRole("ADMIN", "SUPERVISOR")
                .antMatchers("/utente/list", "utente/elimina/**")
                .hasRole("ADMIN")
//		.antMatchers("/**")
//		.permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(filtroAutenticazioneJwt, UsernamePasswordAuthenticationFilter.class);

        http.cors();

        return http.build();
    }

    public SecurityConfiguration() {

    }
}
