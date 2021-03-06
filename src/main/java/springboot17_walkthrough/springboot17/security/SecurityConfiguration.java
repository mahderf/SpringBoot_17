package springboot17_walkthrough.springboot17.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import springboot17_walkthrough.springboot17.repository.UserRepository;
import springboot17_walkthrough.springboot17.service.SSUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

@Autowired
private SSUserDetailsService userDetailsService;
@Autowired
private UserRepository userRepository;

//allows userDetailservice to use this repo
@Override
public UserDetailsService userDetailsServiceBean() throws Exception{
    return new SSUserDetailsService(userRepository);
}
@Override
protected void configure(HttpSecurity http) throws Exception {

    http
            .authorizeRequests()
//            .antMatchers("/admin").hasRole("ADMIN or USER")
            .antMatchers("/","/signup","/css/**","/js/**",
                    "/img/**","/vendor/**","/scss/**","/register").permitAll()
//
            .antMatchers("/admin").access("hasAuthority('ADMIN')")

            //doesn't work on giving access f the url to the roles
//            .antMatchers("/admin").hasRole("ADMIN")
//            .antMatchers("/user/login").permitAll()
//            .antMatchers("/usertest").access("hasRole('ROLE_USER')")
//            .antMatchers("/admin/**").hasRole("ADMIN")as
//            .antMatchers("/admin/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .defaultSuccessUrl("/index")
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))// we don't need an extra page for logout this will do the trick
            .logoutSuccessUrl("/login").permitAll().permitAll()// we're telling it to go back to the login page after logout
            .and()
            .httpBasic();// this allows to login using the password and user in the console, it doesn't matter if we take it out

    http
            .csrf().disable();
    http
            .headers().frameOptions().disable();
}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsServiceBean());
//                inMemoryAuthentication().
//                withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("dave").password("begreat").roles("ADMIN");
    }


}
