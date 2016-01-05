package com.challenger.config;

import com.challenger.security.EmailUserDetailsService;
import com.challenger.security.authentication.CustomAccessDeniedHandler;
import com.challenger.security.authentication.CustomAuthenticationRequiredHandler;
import com.challenger.security.authentication.CustomAuthenticationSuccessHandler;
import com.challenger.security.authentication.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Maksym_Baziuk on 15.12.2015.
 */
@Configuration
@EnableWebSecurity
//@EnableWebMvc
@EnableGlobalMethodSecurity( prePostEnabled = true )
@ComponentScan("com.challenger")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;
    @Autowired
    private EmailUserDetailsService emailUserDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private CustomAuthenticationRequiredHandler authenticationRequiredHandler;

    @Bean
    public BCryptPasswordEncoder getShaPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder, BCryptPasswordEncoder passwordEncoder) throws Exception{
        authBuilder.userDetailsService(emailUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Static content is out of security
        web.ignoring().antMatchers(env.getRequiredProperty("static.content.pattern"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/user/**").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers("/challenge/**").hasRole("ADMIN")
                .antMatchers("/event/**").hasAnyRole(new String[]{"ADMIN","USER"})
                .antMatchers("/user/login**").permitAll()
                .antMatchers("/user/register").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .passwordParameter("password")
                .usernameParameter("username")
                .successHandler(authenticationSuccessHandler).failureHandler(authenticationRequiredHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        //TODO Security part. Look on this later
        http.csrf().disable();
    }
}
