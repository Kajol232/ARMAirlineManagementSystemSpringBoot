package com.example.muhammad.armairlinemanagementsystem.Security;

import com.example.muhammad.armairlinemanagementsystem.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDetailsServiceImpl userDetailsService;




    @Override
   protected void configure(final HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/").permitAll()
                //.antMatchers("/myPage").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/contact").permitAll()
                .antMatchers("/layout").permitAll()
                .antMatchers("/users/create").permitAll()
                .antMatchers("/admins/create").permitAll()
                .antMatchers("/login*").permitAll()

//                .antMatchers("/sendEmail").hasRole("Admin")
                //.antMatchers("/admins/create").hasRole("Admin")
                .antMatchers("/admins/add").hasRole("Admin")
                .antMatchers("/admins/list").hasRole("Admin")
                .antMatchers("/users/list").hasRole("Admin")
                .antMatchers("/aircrafts/**").hasRole("Admin")
                .antMatchers("/flights/**").hasRole("Admin")
                .antMatchers("/bookings/**").permitAll()

                /**.antMatchers("/staffs/removeRole/**").hasRole("ADMIN")
                .antMatchers("/staffs/assignRole/**").hasRole("ADMIN")
                .antMatchers("/users/moreInfo/**").hasRole("STAFF")
                .antMatchers("/categories/**").hasRole("STAFF")
                .antMatchers("/roles/**").hasRole("STAFF")*/

                //.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")

                 //.loginProcessingUrl("/login")
                .defaultSuccessUrl("/myPage", true)
                 //.usernameParameter("username").passwordParameter("password")
                .failureUrl("/login.html?error=true")
                //.failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                //.permitAll()
                //.and()
                //.httpBasic();
                .deleteCookies("JSESSIONID");
        //.logoutSuccessHandler(logoutSuccessHandler());
    }

   // @Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //auth
               // .userDetailsService(this.userDetailsService)
               // .passwordEncoder(passwordEncoder());
    //}

   // @Override
   // protected void configure(final AuthenticationManagerBuilder auth) throws Exception{

        //auth.inMemoryAuthentication()
                //.withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
               // .and()
                //.withUser("admin").password(passwordEncoder().encode("adminPassword")).roles("ADMIN");
    //}

   @Override
   public void configure(WebSecurity web) throws Exception{
        web
                .ignoring()
                .antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/jQuery/**",
                        "/login/**","/**");
   }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
