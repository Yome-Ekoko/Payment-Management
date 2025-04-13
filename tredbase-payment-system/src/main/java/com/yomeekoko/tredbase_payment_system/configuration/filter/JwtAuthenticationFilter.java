package com.yomeekoko.tredbase_payment_system.configuration.filter;

import com.yomeekoko.tredbase_payment_system.configuration.jwt.JwtTokenService;
import com.yomeekoko.tredbase_payment_system.exception.UserNotFoundException;
import com.yomeekoko.tredbase_payment_system.persistence.models.auth.Token;
import com.yomeekoko.tredbase_payment_system.persistence.repository.TokenRepository;
import com.yomeekoko.tredbase_payment_system.service.interfaces.auth.UserService;
import com.yomeekoko.tredbase_payment_system.utils.constants.Message;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = jwtTokenService.extractJwtFromRequest(request);
        if (!StringUtils.hasText(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<Token> token = tokenRepository.findByToken(jwt);
        if (!validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired token");
            return;
        }

        String username = jwtTokenService.extractUsername(jwt);
        UserDetails userDetails = userService.findOneByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }

    private boolean validateToken(Optional<Token> optionalToken) {
        if (optionalToken.isEmpty()) {
            System.out.println("Token does not exist");
            return false;
        }
        Token token = optionalToken.get();
        Date now = new Date(System.currentTimeMillis());
        return token.isValid() && token.getExpiration().after(now);
    }
}