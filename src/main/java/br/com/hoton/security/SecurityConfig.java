package br.com.hoton.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.hoton.UserDetailServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DaoAuthenticationProvider provider;
	
	@Autowired
	private UserDetailServiceImp service;
	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//        .withUser("user1").password(passwordEncoder.encode("user1Pass")).roles("USER")
//        .and()
//        .withUser("user2").password(passwordEncoder.encode("user2Pass")).roles("USER")
//        .and()
//        .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
//    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
	}


	@Override
	protected void configure(final HttpSecurity http) throws Exception {
	    http
	      .csrf().disable()
	      .authorizeRequests()
	      .antMatchers("/logged/**").hasAnyRole("ADMIN", "USER")
	      .antMatchers("/admin/**").hasAnyRole("ADMIN", "USER")
	      .antMatchers("/login/*").permitAll()
	      .antMatchers("/resources/**").permitAll()
	      .antMatchers("/public/**").permitAll()
	      .antMatchers("/*").permitAll()
	      .antMatchers("/api/**").permitAll()
	      .anyRequest().authenticated()
	      .and().formLogin()
	      .loginPage("/login")
	      .usernameParameter("u").passwordParameter("p")
	      .loginProcessingUrl("/perform_login")
	      .defaultSuccessUrl("/admin/home", true)
	      .failureUrl("/login?error=true")
	      .and()
	      .logout()
	      .logoutUrl("/perform_logout")
	      .deleteCookies("JSESSIONID")
	      .logoutSuccessUrl("/login")
	      .and()
          .rememberMe().key("31hotOn07Secret2021").userDetailsService(service);
	}

	
}
