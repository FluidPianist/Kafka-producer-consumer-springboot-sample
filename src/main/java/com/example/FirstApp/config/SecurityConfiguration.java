package com.example.FirstApp.config;
import com.example.FirstApp.security.PassPhraseSecurityFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private PassPhraseSecurityFilter filter;

    private final Log logger = LogFactory.getLog(this.getClass());

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize)-> authorize.requestMatchers("/api/v1/kafka/publish_hello").authenticated()).csrf((csrf)->csrf.disable());
        http.addFilterBefore(filter, BasicAuthenticationFilter.class);
        return http.build();
    }
}
