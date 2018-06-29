package com.codecool.labourent.config;

import com.codecool.labourent.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptEncoder;

    private final UserAccountService userAccountService;

    private final CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    public WebSecurityConfig(UserAccountService userAccountService, CustomAuthenticationSuccessHandler successHandler) {
        this.bCryptEncoder = bCryptPasswordEncoder();
        this.userAccountService = userAccountService;
        this.successHandler = successHandler;
    }

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/introduction", "/login", "/registration", "/list").permitAll()
                .antMatchers("/profile", "/add-service").hasAuthority("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/css/**", "/script/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureUrl("/login?invalid=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/list")
                .and()
                .logout()
                .invalidateHttpSession(true).logoutSuccessUrl("/list")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAccountService)
                .passwordEncoder(bCryptEncoder);

        auth.inMemoryAuthentication()
                .passwordEncoder(bCryptEncoder)
                .withUser("admin@labourent.com")
                .password(bCryptEncoder.encode("admin"))
                .roles("ADMIN");
    }
}
