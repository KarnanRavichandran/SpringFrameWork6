package com.timothy.securitydemo.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//@Configuration
public class BasicAuthSecurityConfiguration{
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                auth-> {auth.anyRequest().authenticated();}
        );
        http.httpBasic();	//BasicAuthPopUp window for login
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        var user = User.withUsername("paul")
//                .password("{noop}timo")
//                .authorities("read")
//                .roles("USER")
//                .build();
//        var admin= User.withUsername("admin")
//                .password("{noop}password")
//                .authorities("read")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        var user = User.withUsername("paul")
//                .password("{noop}timo")
                .password("timo")
                .passwordEncoder(s -> bCryptPasswordEncoder().encode(s))
                .authorities("read")
                .roles("USER")
                .build();
        var admin= User.withUsername("admin")
                .password("{noop}password")
                .authorities("read")
                .roles("ADMIN")
                .build();
       var jdbcUserDetailsManager= new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}