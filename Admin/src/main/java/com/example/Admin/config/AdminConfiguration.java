package com.example.Admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AdminConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new AdminServiceConfig();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin/**").permitAll()
                .antMatchers(HttpMethod.GET, "/product/**").permitAll()
                .antMatchers(HttpMethod.GET, "/category/**").permitAll()
                .antMatchers(HttpMethod.GET, "/role/**").permitAll()
                .antMatchers(HttpMethod.GET, "/order/**").permitAll()

                .antMatchers(HttpMethod.POST, "/admin/**").permitAll()
                .antMatchers(HttpMethod.POST, "/product/**").permitAll()
                .antMatchers(HttpMethod.POST, "/category/**").permitAll()
                .antMatchers(HttpMethod.POST, "/role/**").permitAll()
                .antMatchers(HttpMethod.POST, "/order/**").permitAll()

                .antMatchers(HttpMethod.DELETE, "/admin/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/product/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/category/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/role/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/order/**").permitAll()

                .antMatchers(HttpMethod.PUT, "/admin/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/product/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/category/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/role/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/order/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/hello")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
