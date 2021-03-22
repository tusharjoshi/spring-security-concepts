package com.company.bookapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.company.bookapi.security.UserPermission.BUNDLE_WRITE;
import static com.company.bookapi.security.UserRole.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                    .permitAll()
                .antMatchers("/api/**").hasRole(MEMBER.name())
                .antMatchers(HttpMethod.DELETE, "/admin/api/**")
                    .hasAuthority(BUNDLE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/admin/api/**")
                    .hasAuthority(BUNDLE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/admin/api/**")
                    .hasAuthority(BUNDLE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/admin/api/**")
                    .hasAnyRole(ADMIN.name(), ADMIN_SUB_CON.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails rajUser = User.builder()
                .username("raj")
                .password(passwordEncoder.encode("password"))
//                .roles(MEMBER.name())
                .authorities(MEMBER.getGrantedAuthorities())
                .build();

        UserDetails rishiUser = User.builder()
                .username("rishi")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails ranbirUser = User.builder()
                .username("ranbir")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN_SUB_CON.name())
                .authorities(ADMIN_SUB_CON.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
            rajUser,
            rishiUser,
            ranbirUser
        );
    }
}
