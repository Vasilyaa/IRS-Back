package net.redciscso.javapmproject.security;


import net.redciscso.javapmproject.controllers.LoginController;
import net.redciscso.javapmproject.domain.enums.RoleEnum;
import net.redciscso.javapmproject.security.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
//аннотация из пакета спринг секьюрити
//включ механизм безопасости в проекте исходя из конфигов
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(getTokenFilter(), BasicAuthenticationFilter.class)
                .antMatcher("/***")
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .antMatchers("/user/info").hasAnyAuthority(RoleEnum.STUDENT.name(),RoleEnum.TEACHER.name())
                .antMatchers("/article/get/{id}").hasAnyAuthority(RoleEnum.STUDENT.name(),RoleEnum.TEACHER.name())
                .antMatchers("/commentary/add", "/commentary/delete/{id}").hasAnyAuthority(RoleEnum.TEACHER.name())
                .antMatchers("/commentary/delete/{id}").hasAnyAuthority(RoleEnum.TEACHER.name())
                .antMatchers("/header/add", "/header/all", "/header/update", "/header/delete/{id}").hasAnyAuthority(RoleEnum.TEACHER.name())
                .antMatchers("/image/delete/{id}", "/image/deleteAll").hasAnyAuthority(RoleEnum.TEACHER.name())
                .antMatchers(LoginController.loginUri)
                .permitAll();
        http.csrf().disable();
    }





    @Bean
    public TokenFilter getTokenFilter() {
        return new TokenFilter(authenticationProvider);
    }

}

