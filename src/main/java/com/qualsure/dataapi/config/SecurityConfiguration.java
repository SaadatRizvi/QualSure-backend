package com.qualsure.dataapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.qualsure.dataapi.service.UsersService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsersService userDetailsService;
	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private CORSFilter myCorsFilter;
	
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("HEAD",
//                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//        
//        // setAllowCredentials(true) is important, otherwise:
//        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//        configuration.setAllowCredentials(true);
//        // setAllowedHeaders is important! Without it, OPTIONS preflight request
//        // will fail with 403 Invalid CORS request
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		   auth.userDetailsService(userDetailsService)
           .passwordEncoder(encoder());
//			.passwordEncoder(getPasswordEncoder());

		   
		   
		   
		   //		auth
//        .inMemoryAuthentication()
//            .withUser("user").password("user").roles("USER");
	}
	
	@Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/hello").permitAll()
			.antMatchers("/universities","/universities/*").permitAll()
			.antMatchers("/validators","/validators/*").permitAll()
			.antMatchers("/fixedFormFields","/fixedFormFields/*").permitAll()
			.antMatchers("/token/*", "/signup" ).permitAll()
			.anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http
            .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		http.addFilterBefore(myCorsFilter, ChannelProcessingFilter.class);

	}


	 @Bean
	    public BCryptPasswordEncoder encoder(){
	        return new BCryptPasswordEncoder();
	    }
	
	 
	 //use this when you dont want any encoding on password
	 
//	private PasswordEncoder getPasswordEncoder() {
//		return new PasswordEncoder() {
//
//			@Override
//			public String encode(CharSequence charSequence) {
//				return charSequence.toString();
//			}
//
//			@Override
//			public boolean matches(CharSequence charSequence, String s) {
//				return true;
//			}
//			
//		};
//	}


	
	
}
