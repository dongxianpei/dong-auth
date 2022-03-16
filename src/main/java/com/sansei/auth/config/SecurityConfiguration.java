package com.sansei.auth.config;

import com.alibaba.fastjson.JSON;
import com.sansei.auth.web.CustomLogoutSuccessHandler;
import com.sansei.base.rpc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/11 15:12
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().permitAll().and().csrf().disable().formLogin().permitAll().successHandler((request, response, authentication) -> {
            response.getWriter().write(JSON.toJSONString(Result.success((Object) null)));
        }).and().logout().logoutUrl("/user/logout").logoutSuccessHandler(new CustomLogoutSuccessHandler()).and().httpBasic();
    }


}
