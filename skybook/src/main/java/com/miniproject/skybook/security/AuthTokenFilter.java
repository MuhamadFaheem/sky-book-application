package com.miniproject.skybook.security;

import com.miniproject.skybook.services.UserServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JWTUtils jwTutil;
    private final UserServices userServices;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //Authentication
            String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
            String token = null;
            if(headerAuth != null && headerAuth.startsWith("Bearer ")){
                token = headerAuth.substring(7);
            }

            //Authorization
            if(token != null && jwTutil.verifyJWTToken(token)){
                Map<String,String> userInfo = jwTutil.getUserInfobyToken(token);
                UserDetails userDetails = userServices.loadUserbyUserId(userInfo.get("userId"));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());//validate token
                authenticationToken.setDetails(new WebAuthenticationDetailsSource()); //adding IP address information to security
                SecurityContextHolder.getContext().setAuthentication(authenticationToken); //save auth to spring
            }
        }catch (Exception e){
            e.getMessage();
            System.out.println("error :"+e);
        }

        filterChain.doFilter(request,response);
    }
}
