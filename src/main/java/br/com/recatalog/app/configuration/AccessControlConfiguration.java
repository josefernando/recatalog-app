package br.com.recatalog.app.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity   // specialized bean that brings an auth object
public class AccessControlConfiguration extends WebSecurityConfigurerAdapter{

/*
 * In Memory Authentication 
 */	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.inMemoryAuthentication()
//			.withUser("blah")
//			.password("blah")
//			.roles("USER")
//			.and()
//			.withUser("foo")
//			.password("foo")
//			.roles("ADMIN");
//	}
	
/*
 * JDBC DAO
 */	
//	@Autowired
//    @Qualifier("securityDataSource") // "securityDataSource" is defined in "SecurityDataSourcesConfig" file as a @Bean name
//	DataSource jdbcDataSource;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.jdbcAuthentication()
//			.dataSource(jdbcDataSource);
//	}
	
//***	
// UserDetailsService 
//***	
	@Autowired
	UserDetailsService customUserDetailsService;
	
	@Autowired
	UserDetailsService accountDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		auth.userDetailsService(customUserDetailsService);
		auth.userDetailsService(accountDetailsService);

	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/recatalog/projects/**").hasAnyRole("USER")
			.antMatchers("/recatalog/catalogs/**").hasAnyRole("ADMIN", "USER")
			.antMatchers("/recatalog").permitAll()
			.antMatchers("/").permitAll()
			.and()
			.formLogin().loginPage("/login").permitAll()
		    .and()
		    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		    	.logoutSuccessUrl("/recatalog");		
		    	.logoutSuccessUrl("/recatalog");		    	
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}