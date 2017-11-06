package com.kilfat.config.security;

import static com.kilfat.config.ServiceConstants.REALM;

import com.kilfat.database.service.implementations.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan({"com.kilfat"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public SecurityConfig(SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("admin").password("password123").roles("ADMIN").authorities(new SimpleGrantedAuthority("ADMIN"));
        auth.userDetailsService(getUserDetailService());//.passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests().anyRequest().authenticated();
        http.httpBasic().realmName(REALM)
            .authenticationEntryPoint(getRestAuthenticationEntryPoint());
//            .authorizeRequests()
//            .antMatchers("/**").authenticated()
//            .and().httpBasic().realmName(REALM).authenticationEntryPoint(getRestAuthenticationEntryPoint());
//            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        http.csrf().disable()
////                .exceptionHandling()
//            .httpBasic()
//            .authenticationEntryPoint(restAuthenticationEntryPoint)
//            .and()
//            .authorizeRequests()
//            .antMatchers("/**").authenticated()
////                .and()
////                .formLogin()
////                .successHandler(authenticationSuccessHandler)
////                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
//            .and()
//            .logout();

        //
//        http.authorizeRequests().antMatchers("/admin/**")
//                .access("hasRole('ROLE_ADMIN')").and().formLogin()
//                .loginPage("/login").failureUrl("/login?error")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and().logout().logoutSuccessUrl("/login?logout")
//                .and().csrf()
//                .and().exceptionHandling().accessDeniedPage("/403");
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public RestAuthenticationEntryPoint getRestAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public UserDetailsService getUserDetailService() {
        return new UserDetailServiceImpl();
    }
}
