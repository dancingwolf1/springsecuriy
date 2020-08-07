package com.yiyuclub.springsecurity.config;

import com.yiyuclub.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SelfAuthenticationEntryPoint authenticationEntryPoint;  //  未登陆时返回 JSON 格式的数据给前端（否则为 html）

    @Autowired
    SelfAuthenticationSuccessHandler authenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    SelfAuthenticationFailureHandler authenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    SelfLogoutSuccessHandler logoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Autowired
    SelfAccessDeniedHandler accessDeniedHandler;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    @Autowired
    UserService userService; // 自定义user(返回UserDetail)

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
        //查看密码
//        String encode = new BCryptPasswordEncoder().encode("111111");
//        System.out.println("user:"+encode);

        //自定义登陆身份判断
//        auth.authenticationProvider(myAuthenticationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //允许所有请求通过Http Basic 验证
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                //访问应用的所有用户都要被验证
                .authorizeRequests()
                .anyRequest()
                .access("@PermissionConfig.hasPermission(request,authentication)")
                .and()
                //用户可以通过表单进行验证
                .formLogin()
                //.loginPage( "/login")// 1 如果没有此行指定，则会使用内置的登陆页面
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                /*注销处理
                1、使HttpSession失效
                2、清空已配置的RememberMe验证
                3、清空 SecurityContextHolder
                4、重定向到 /login?success
                一般情况下，为了自定义注销功能，你可以添加 LogoutHandler 或者 LogoutSuccessHandler 的实现。
                 */
                //1、提供注销支持，当使用 WebSecurityConfigurerAdapter时这将会被自动应用
                .logout()
                //触发注销操作的url，默认是/logout。如果开启了CSRF保护(默认开启),那么请求必须是POST方式。
//                .logoutUrl("/my/logout")
                //注销操作发生后重定向到的url，默认为 /login?logout。
//                .logoutSuccessUrl("/my/index")
                //让你指定自定义的 LogoutSuccessHandler。如果指定了， logoutSuccessUrl() 将会被忽略。
                .logoutSuccessHandler(logoutSuccessHandler)
                //指定在注销的时候是否销毁 HttpSession 。默认为True。
//                .invalidateHttpSession(true)
                //添加一个 LogoutHandler。默认情况下， SecurityContextLogoutHandler 被作为最后一个 LogoutHandler 。
//                .addLogoutHandler(logoutHandler)
                //7、允许指定当注销成功时要移除的cookie的名称。这是显式添加 CookieClearingLogoutHandler 的一种快捷处理方式。
//                .deleteCookies(cookieNamesToClear)
                .permitAll();

        // 记住我
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(userService).tokenValiditySeconds(300);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
        //      http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter

    }

    //官方推荐使用BCrypt加密
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
