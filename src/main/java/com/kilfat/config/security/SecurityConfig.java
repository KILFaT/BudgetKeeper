package com.kilfat.config.security;

import static com.kilfat.config.ServiceConstants.API_URL;
import static com.kilfat.config.ServiceConstants.REALM;

import com.kilfat.config.security.utils.CORSFilter;
import com.kilfat.config.security.utils.CSRFFilter;
import com.kilfat.config.security.utils.RestAuthenticationEntryPoint;
import com.kilfat.config.security.utils.RestAuthenticationSuccessHandler;
import com.kilfat.config.security.utils.RestLogoutSuccessHandler;
import com.kilfat.database.service.implementations.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan({"com.kilfat"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public SecurityConfig(SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler) {
//        this.authenticationSuccessHandler = authenticationSuccessHandler;
//    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailService());//.passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers("/**").authenticated()
            .anyRequest().permitAll();

        http.httpBasic().realmName(REALM)
            .authenticationEntryPoint(restAuthenticationEntryPoint())
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint());
        http.formLogin().successHandler(authenticationSuccessHandler());
        http.formLogin().failureHandler(authenticationFailureHandler());
        http.addFilterBefore(corsFilter(), ChannelProcessingFilter.class);
        http.csrf().requireCsrfProtectionMatcher(
            new AndRequestMatcher(
                new NegatedRequestMatcher(new AntPathRequestMatcher(API_URL + "**", HttpMethod.GET.toString())),
                new NegatedRequestMatcher(new AntPathRequestMatcher(API_URL + "**", HttpMethod.HEAD.toString())),
                new NegatedRequestMatcher(new AntPathRequestMatcher(API_URL + "**", HttpMethod.OPTIONS.toString())),
                new NegatedRequestMatcher(new AntPathRequestMatcher(API_URL + "**", HttpMethod.TRACE.toString())),
                new NegatedRequestMatcher(new AntPathRequestMatcher(API_URL + "**"))
            )
        );
        http.addFilterAfter(new CSRFFilter(), CsrfFilter.class); // CSRF tokens handling

    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public UserDetailsService getUserDetailService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    public RestAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler();
    }

    @Bean
    public CORSFilter corsFilter() {
        return new CORSFilter();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new RestLogoutSuccessHandler();
    }
}
