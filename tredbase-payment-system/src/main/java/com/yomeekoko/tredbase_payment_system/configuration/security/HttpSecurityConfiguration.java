package com.yomeekoko.tredbase_payment_system.configuration.security;

import com.yomeekoko.tredbase_payment_system.configuration.filter.JwtAuthenticationFilter;
import com.yomeekoko.tredbase_payment_system.configuration.handler.CustomAccessDeniedHandler;
import com.yomeekoko.tredbase_payment_system.configuration.handler.CustomAuthenticationEntryPoint;
import com.yomeekoko.tredbase_payment_system.utils.enums.PermissionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class HttpSecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exceptions) -> {
                    exceptions.authenticationEntryPoint(customAuthenticationEntryPoint);
                    exceptions.accessDeniedHandler(customAccessDeniedHandler);
                })
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList( "http://www.google.com", ""));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "Accept", "Origin"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(300L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authHttp) {
        authHttp.requestMatchers("/error").permitAll();
        authHttp.requestMatchers(HttpMethod.GET, "/parents/**").permitAll();
        authHttp.requestMatchers(HttpMethod.GET, "/students/**").permitAll();
        authHttp.requestMatchers(HttpMethod.GET, "/payments/**").permitAll();
        authHttp.requestMatchers(HttpMethod.GET, "/accounts/**").permitAll();
        authHttp.requestMatchers(HttpMethod.GET, "/rates/**").permitAll();
        authHttp.requestMatchers(HttpMethod.POST, "/administrator/register").permitAll();
        authHttp.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
        authHttp.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();


        // Parents
        authHttp.requestMatchers(HttpMethod.POST, "/parent/create-parent")
                .hasAuthority(PermissionEnum.CREATE_ONE_PARENT.name());

        /*authHttp.requestMatchers(HttpMethod.PUT, "/parent/update/{parentId}")
                .hasAuthority(PermissionEnum.UPDATE_ONE_PARENT.name());

         */

        
        authHttp.requestMatchers(HttpMethod.DELETE, "/parent/delete/{parentId}")
                .hasAuthority(PermissionEnum.DELETE_ONE_PARENT.name());

        // Student
        authHttp.requestMatchers(HttpMethod.POST, "/student/create-student")
                .hasAuthority(PermissionEnum.CREATE_ONE_STUDENT.name());


        authHttp.requestMatchers(HttpMethod.DELETE, "/student/delete/{id}")
                .hasAuthority(PermissionEnum.DELETE_ONE_STUDENT.name());
        authHttp.anyRequest().authenticated();


//Admins
        authHttp.requestMatchers(HttpMethod.POST, "/administrator/register")
                .hasAuthority(PermissionEnum.ADD_ADMINISTRATORS.name());


//Payment
        authHttp.requestMatchers(HttpMethod.POST, "/payments/make-payment")
                .hasAuthority(PermissionEnum.PROCESS_ALL_PAYMENT.name());

//Rate
        authHttp.requestMatchers(HttpMethod.POST, "/rates/add")
                .hasAuthority(PermissionEnum.PROCESS_ALL_PAYMENT.name());


    }
}
