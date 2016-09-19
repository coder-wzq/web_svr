package cc.blueview.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import cc.blueview.authentication.CustomAuthenticationProvider;
import cc.blueview.authentication.RestAuthenticationAccessDeniedHandler;
import cc.blueview.authentication.RestAuthenticationEntryPoint;
import cc.blueview.authentication.RestAuthenticationFailureHandler;
import cc.blueview.authentication.RestAuthenticationSuccessHandler;
import cc.blueview.authentication.RestLogoutSuccessHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private RestLogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private RestAuthenticationAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.POST, "/logout").authenticated().antMatchers("/bvc/test/hello").permitAll()//表示该路径下的资源不需要登录
				.antMatchers("/bvc/**").authenticated().and().formLogin()
				.successHandler(restAuthenticationSuccessHandler).failureHandler(restAuthenticationFailureHandler)
				.loginProcessingUrl("/login").and().logout().logoutSuccessHandler(logoutSuccessHandler)
				.logoutSuccessUrl("/logout").and().sessionManagement().maximumSessions(10);

		http.authenticationProvider(customAuthenticationProvider()).exceptionHandling()
				.authenticationEntryPoint(restAuthenticationEntryPoint).accessDeniedHandler(accessDeniedHandler).and()
				.headers().frameOptions().disable();

	}

	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}

	@Bean
	public RestAuthenticationSuccessHandler restAuthenticationSuccessHandler() {
		return new RestAuthenticationSuccessHandler();
	}

	@Bean
	public RestAuthenticationFailureHandler restAuthenticationFailureHandler() {
		return new RestAuthenticationFailureHandler();
	}

	@Bean
	public RestLogoutSuccessHandler logoutSuccessHandler() {
		return new RestLogoutSuccessHandler();
	}

	@Bean
	public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public RestAuthenticationAccessDeniedHandler accessDeniedHandler() {
		return new RestAuthenticationAccessDeniedHandler();
	}

}