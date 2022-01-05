package com.oth.sentforward.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SentForwardSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userSecurityService;

    @Autowired
    private SentForwardSecurityUtils securityUtils;

    private BCryptPasswordEncoder passwordEncoder()
    {
        return securityUtils.passwordEncoder();
    }

    private static final String[] ALLOW_ACCESS_WITHOUT_AUTHENTICATION = { "/login", "/calendars","/register", "/registrationSubmit","/css/**","js/**"};
    private static final String[] ALLOW_ACCESS_WITHOUT_AUTHENTICATION_POST ={"/calendars"};

    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,ALLOW_ACCESS_WITHOUT_AUTHENTICATION)
                    .permitAll()
                .antMatchers(HttpMethod.POST, ALLOW_ACCESS_WITHOUT_AUTHENTICATION_POST)
                    .permitAll()
                .anyRequest()
                .authenticated()
                ;


        http

                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/account",true)
                .failureUrl("/login-Error")


                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("remember-me")
                .permitAll()

                .and()
                    .rememberMe();
        http.csrf().disable();



    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService)
                .passwordEncoder(passwordEncoder());
    }
}
