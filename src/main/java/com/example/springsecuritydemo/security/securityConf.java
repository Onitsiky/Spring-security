package com.example.springsecuritydemo.security;

import com.example.springsecuritydemo.model.MyUser;
import com.example.springsecuritydemo.model.Roles;
import com.example.springsecuritydemo.service.MyUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// Pour dire que c'est cette configuration que spring devra utiliser
@Slf4j // Pour faire des logs
public class securityConf extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(bcryptPasswordEncoder);
        return auth;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/users/**").hasRole("admin")
                .antMatchers(HttpMethod.GET, "/posts/**").hasAnyRole("student", "boss")
                .and()
                .formLogin()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .httpBasic(); //Pour spécifier le système d'authentification : Basic Auth
    }

    /*


    @Bean //fait en sorte que la variable retournée par cette méthode
            // soit détectée par Spring grâce à Spring Application Context
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails student = org.springframework.security.core.userdetails.User.builder()
                .username("onitsiky")
                .password(getPasswordEncoder().encode("oni123"))
                .roles("student")
                .build();

        UserDetails admin = User.builder()
                .username("user")
                .password(getPasswordEncoder().encode("admin"))
                .roles("boss")
                .build();

         log.info("Admin with Bcrypt : " + admin.getPassword()); // C'est un sout mais en mieux
        log.info("Student with Bcrypt : " + student.getPassword()); // C'est un sout mais en mieux
        return new  InMemoryUserDetailsManager(student, admin);
    }
    */
}