package com.example.FirstApp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class PassPhraseSecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {
        logger.info("Executing Authentication");
        String username = httpServletRequest.getParameter("username");
        String secretsPath = System.getenv("SecretsPath");
        if(secretsPath == null) secretsPath="secrets";
        Path passPath = Paths.get(secretsPath,"pass");
        String password = Files.readAllLines(passPath).get(0);
        logger.info("username : %s \n password : %s "+username+password);
        if(username.equals("adeeb") && password.equals("adeeb")){
            Authentication authentication = new UsernamePasswordAuthenticationToken(null,null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("Log in success");
        }else {
            SecurityContextHolder.clearContext();
            logger.info("Log in failure");
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
