package com.athena.judge;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/" , "/home", "/css/**","/js/**","/images/**").permitAll()
		.antMatchers("/ShowProblems/").permitAll() 
		.antMatchers("/Register_User/").permitAll()
		.antMatchers("/Register_User").permitAll() 
		.antMatchers("/User/Save_User/").permitAll()
		.antMatchers("/api/SaveUser/**").permitAll()
		.antMatchers("/Training/**").permitAll() 
		.antMatchers("/api/**").permitAll()
		.antMatchers("/api/Contest/**").permitAll()
		.antMatchers("/User/Save_User/").permitAll()
		.antMatchers("/New_Register_User").permitAll()
		.antMatchers("/Info").permitAll()
		.antMatchers("/Info/**").permitAll()
		.antMatchers("/Save_New_User").permitAll() 
		.antMatchers("/Save_New_User/").permitAll() 
		.antMatchers("/Save_User").permitAll()
		.antMatchers("/try_js/").permitAll() 
		.antMatchers("/RankList/").permitAll()
		.antMatchers("/ShowAlgorithms/").permitAll()
		.antMatchers("/User/Submit/**").hasAnyRole("USER")
		.antMatchers("/User/Profile/**").hasAnyRole("USER")
		.antMatchers("/Admin/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
	}
	
	
	@Autowired
	public void ConfigurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception  {
		builder.jdbcAuthentication()
		.dataSource(datasource)
		.passwordEncoder(passEncoder)
		.usersByUsernameQuery("select email_address, password, enabled from athena.table_user where email_address =?")
		.authoritiesByUsernameQuery("select u.email_address , r.role from athena.table_role r join athena.table_user u on r.id_user = u.id_user where u.email_address =?");
	}

}
