package edu.miu.cs.cs425.mystudentmgmtappwebsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class StudentRegisterWebSecurityConfig {

    private UserDetailsService userDetailsService;

    public StudentRegisterWebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                .cors()
//                .and()
//                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin()
                .and()
//                .authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .authorizeRequests()
                .antMatchers("/resources/static/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/", "/public/home", "/fairfieldlibary").permitAll()
                .antMatchers("/mystudentmgmtappwebsecurity/public/**").permitAll()
                .antMatchers("/mystudentmgmtappwebsecurity/secured/sysadmin/**").hasRole("ADMIN")
                .antMatchers("/mystudentmgmtappwebsecurity/secured/services/register/**").hasRole("REGISTER")
                .antMatchers("/mystudentmgmtappwebsecurity/secured/services/student/**").hasRole("STUDENT")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/mystudentmgmtappwebsecurity/public/login")
                .defaultSuccessUrl("/mystudentmgmtappwebsecurity/public/home")
                .failureUrl("/mystudentmgmtappwebsecurity/public/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/fairfieldlibrary/public/logout"))
                .logoutSuccessUrl("/mystudentmgmtappwebsecurity/public/login?logout")
                .permitAll()
                .and()
                .exceptionHandling();
        httpSecurity.authenticationProvider(authenticationProvider());
        return httpSecurity.build();
    }


}
