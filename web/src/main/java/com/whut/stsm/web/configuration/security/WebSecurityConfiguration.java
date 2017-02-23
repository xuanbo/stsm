package com.whut.stsm.web.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * Security
 *
 * Created by null on 2017/2/22.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/", "/index").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                    .successHandler(new RestAuthenticationSuccessHandler())
                    .failureHandler(new RestAuthenticationFailureHandler())
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(new RestLogoutSuccessHandler());
    }

    /**
     * 全局安全方法必须配置authenticationManagerBean
     *
     * @return AuthenticationManagerDelegator
     * @throws Exception 异常信息
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 自定义AuthenticationProvider
        auth
                .authenticationProvider(authenticationProvider());
    }

    /**
     * 自定义身份认证Provider
     * 默认是DaoAuthenticationProvider
     * 实现AuthenticationProvider，自定义自己的身份认证Provider
     *
     * @return LimitLoginAuthenticationProvider 对登录失败尝试限制
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        LimitLoginAuthenticationProvider authenticationProvider = new LimitLoginAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * 自定义UserDetailsService
     * 主要是根据username获取UserDetails信息
     * 跟Shiro的Realm类似
     *
     * @return MyUserDetailsServiceImpl
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsServiceImpl();
    }

    /**
     * 自定义认证成功处理
     * 实现了AuthenticationSuccessHandler，返回成功的json数据
     *
     * @return RestAuthenticationSuccessHandler 返回成功的json数据
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler();
    }

    /**
     * 自定义认证失败处理
     * 实现了AuthenticationFailureHandler，根据身份认证Provider抛出的身份认证异常做不同的处理，返回失败的json数据
     *
     * @return RestAuthenticationFailureHandler  返回身份认证失败信息，返回失败的json数据
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }

    /**
     * 自定义注销成功处理
     * 实现LogoutSuccessHandler，注销返回json
     *
     * @return RestLogoutSuccessHandler 注销返回json
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new RestLogoutSuccessHandler();
    }

    /**
     * 加密
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(9);
    }
}
