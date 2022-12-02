package com.ibaezar.springboot.jpa.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/", "/css/**", "/js/**", "/img/**", "/lenguage").permitAll()
        .antMatchers("/clientes/listar/**").permitAll()
        .antMatchers("/clientes/detalle/**").hasAnyRole("USER")
        .antMatchers("/clientes/form/**").hasAnyRole("ADMIN")
        .antMatchers("/clientes/eliminar/**").hasAnyRole("ADMIN")
        .antMatchers("/facturas/detalle/**").hasAnyRole("USER")
        .antMatchers("/facturas/form/**").hasAnyRole("ADMIN")
        .antMatchers("/facturas/eliminar/**").hasAnyRole("ADMIN")
        .anyRequest().authenticated()
        .and()
            .formLogin().loginPage("/login")
            .permitAll()
        .and()
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/error_403");        
        return http.build();
    }

    @Bean
	public UserDetailsService userDetailsService() throws Exception {
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("izhar")
        .password(passwordEncoder().encode("123456"))
        .roles("USER").build());

        manager.createUser(User.withUsername("admin")
        .password(passwordEncoder().encode("123456"))
        .roles("ADMIN", "USER").build());
    
        return manager;
    }

        
    /*
    ? este metodo se usaba con extends webSecurityConfigurerAdapter, pero está deprecated
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
        
        PasswordEncoder encoder = passwordEncoder();
        UserBuilder users = User.builder().passwordEncoder(encoder::encode);

        builder.inMemoryAuthentication()
        .withUser(users.username("admin").password("123456").roles("ADMIN", "USER"))
        .withUser(users.username("izhar").password("123456").roles("USER"));
    }
    */
}
