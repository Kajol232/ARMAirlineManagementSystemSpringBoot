package com.example.muhammad.armairlinemanagementsystem.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
   protected void configure(final HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/contact").permitAll()
                .antMatchers("/layout").permitAll()
                .antMatchers("/subs").permitAll()
                .antMatchers("/users/create").permitAll()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/login*").permitAll()

//                .antMatchers("/sendEmail").hasRole("Admin")
                /**.antMatchers("/admins/create").hasRole("Admin")
                .antMatchers("/admins/add").hasRole("STAFF")
                .antMatchers("/admins/list").hasRole("STAFF")
                .antMatchers("/users/list").hasRole("STAFF")
                .antMatchers("/products/list").permitAll()
                .antMatchers("/warehouses/list").hasRole("STAFF")
                .antMatchers("/shipments/list").hasRole("STAFF")

                .antMatchers("/staffs/removeRole/**").hasRole("ADMIN")
                .antMatchers("/staffs/assignRole/**").hasRole("ADMIN")
                .antMatchers("/users/moreInfo/**").hasRole("STAFF")
                .antMatchers("/categories/**").hasRole("STAFF")
                .antMatchers("/roles/**").hasRole("STAFF")*/

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                 .loginProcessingUrl("/loginSecure")
                .defaultSuccessUrl("/myPage", true)
                 .usernameParameter("username").passwordParameter("password")
                .failureUrl("/login.html?error=true")
                //.failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                //.logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID");
        //.logoutSuccessHandler(logoutSuccessHandler());
    }

   @Override
   public void configure(WebSecurity web) throws Exception{
        web
                .ignoring()
                .antMatchers("/static/**", "/bootstrap/**", "/css/**", "/js/**", "/images/**", "/jquery/**");
   }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
