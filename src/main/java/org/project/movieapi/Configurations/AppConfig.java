package org.project.movieapi.Configurations;

import lombok.RequiredArgsConstructor;
import org.project.movieapi.Services.Impl.OMDbClientService;
import org.project.movieapi.Services.UserService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserService userService;

    private final OMDbApiConfig omDbApiConfig;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder()
                .rootUri(omDbApiConfig.getApiUrl())
                .build();
    }

    @Bean
    public OMDbClientService omDbClientService(RestTemplate restTemplate){
        return new OMDbClientService(restTemplate, omDbApiConfig.getApiKey());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService::findByUsername;
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
