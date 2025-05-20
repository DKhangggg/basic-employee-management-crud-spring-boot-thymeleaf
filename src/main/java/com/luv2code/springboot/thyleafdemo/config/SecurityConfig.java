package com.luv2code.springboot.thyleafdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager user = new JdbcUserDetailsManager(dataSource);
        user.setUsersByUsernameQuery("select username,password,enabled from users where username=?");
        user.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
        return user;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .anyRequest().authenticated()
        ).formLogin(form ->
                        form
                                .loginPage("/showLoginPage")
                                .loginProcessingUrl("/authenticateUser")
                                .successHandler((request, response, authentication) -> {
                                    System.out.println("User logged in successfully: " + authentication.getName());
                                    System.out.println("User roles: " + authentication.getAuthorities());
                                    response.sendRedirect("/employees/list");
                                })
                                .failureHandler((request, response, exception) -> {
                                    System.out.println("Login failed for user: " + request.getParameter("username"));
                                    System.out.println("Reason: " + exception.getMessage());
                                    response.sendRedirect("/showLoginPage?error");
                                })
                                .permitAll()
                ).logout(logout ->
                        logout
                                .logoutSuccessHandler((request, response, authentication) -> {
                                    if (authentication != null) {
                                        System.out.println("User logged out: " + authentication.getName());
                                    }
                                    response.sendRedirect("/showLoginPage?logout");
                                })
                                .permitAll()
                );
        return http.build();
    }
}
