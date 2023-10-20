package org.example.authority.config.security.service;

import org.example.authority.config.security.filter.CheckTokenFilter;
import org.example.authority.config.security.handler.AnonymousAuthenticationHandler;
import org.example.authority.config.security.handler.CustomerAccessDeniedHandler;
import org.example.authority.config.security.handler.LoginFailureHandler;
import org.example.authority.config.security.handler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author bingo
 * @description Spring Security配置类
 * @date 2022-11-03
 */
@Configuration
@EnableWebSecurity
//开启权限注解控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomerUserDetailsService customerUserDetailsService;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;
    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;
    @Resource
    private CheckTokenFilter checkTokenFilter;

    /**
     * 注入加密处理类
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /***
     * 处理登录认证
     * @param http:
     * @return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录前进行过滤
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //表单登录
        http.formLogin().loginProcessingUrl("/api/user/login")
                // 设置登录验证成功或失败后的的跳转地址
                .successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
                // 禁用csrf防御机制
                .and().csrf().disable()
                // 不创建Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 设置需要拦截的请求
                .authorizeRequests()
                // 登录请求放行
                .antMatchers("/api/user/login").permitAll()
                // 其他请求一律要求身份认证
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationHandler)       // 匿名无权限访问
                .accessDeniedHandler(customerAccessDeniedHandler)               // 认证无权限访问
                .and().cors();//开启跨域配置
    }

    /***
     * 配置认证处理器
     * @param auth:
     * @return void
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
