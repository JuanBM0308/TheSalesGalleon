package com.juanba.the_sales_galleon.config.security;

import com.juanba.the_sales_galleon.authentication.util.Permission;
import com.juanba.the_sales_galleon.config.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionMangeConfig -> sessionMangeConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {
                    // * Public enpoints
                    // ? Authenthication controller
                    authConfig.requestMatchers(HttpMethod.GET, "/auth/greet-developer").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                    authConfig.requestMatchers("/error").permitAll();

                    // ? Product controller
                    authConfig.requestMatchers(HttpMethod.GET, "/products/api/list-products").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/products/api/find-product/{id}").hasAuthority(Permission.FIND_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/products/api/active-products").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/products/api/list-products/{category}").hasAuthority(Permission.FIND_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/products/api/create").hasAuthority(Permission.SAVE_ONE_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/products/api/delete/{id}").hasAuthority(Permission.DELETE_ONE_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/products/api/update").hasAuthority(Permission.UPDATE_ONE_PRODUCT.name());

                    // ? User controller
                    authConfig.requestMatchers(HttpMethod.GET, "/users/api/list-users").hasAuthority(Permission.READ_ALL_USERS.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/users/api/find-user-by-id/{id}").hasAuthority(Permission.FIND_USER_BY_ID.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/users/api/find-user-by-email/{email}").hasAuthority(Permission.FIND_USER_BY_EMAIL.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/users/api/deactivate-user/{id}").hasAuthority(Permission.DEACTIVATE_USER.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/users/api/activate-user/{id}").hasAuthority(Permission.ACTIVATE_USER.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/users/api/create").hasAuthority(Permission.SAVE_ONE_USER.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/users/api/delete/{id}").hasAuthority(Permission.DELETE_ONE_USER.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/users/api/update").hasAuthority(Permission.UPDATE_ONE_USER.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/users/api/generate-password-recovery-code").hasAuthority(Permission.GENERATE_VERIFICATION_CODE.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/users/api/change-password").hasAuthority(Permission.UPDATE_USER_PASSWORD.name());

                    // ? Category controller
                    authConfig.requestMatchers(HttpMethod.GET, "/categories/api/list-categories").hasAuthority(Permission.READ_ALL_CATEGORIES.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/categories/api/category/{id}").hasAuthority(Permission.FIND_CATEGORY.name());

                    // * Any request deny all
                    authConfig.anyRequest().denyAll();
                });

        return http.build();
    }
}
