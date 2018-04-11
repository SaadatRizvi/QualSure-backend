package com.qualsure.dataapi.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Component
@Order(1)
public class CORSFilter implements Filter{

    // This is to be replaced with a list of domains allowed to access the server
  //You can include more than one origin here
    private final List<String> allowedOrigins = Arrays.asList("http://localhost:4200","/**");
    
public CORSFilter () {
    super();
}

@Override
public final void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
    final HttpServletResponse response = (HttpServletResponse) res;
    final HttpServletRequest request = (HttpServletRequest) req;

    //response.setHeader("Access-Control-Allow-Origin", "*");
        
    // Access-Control-Allow-Origin
    String origin = request.getHeader("Origin");
  //  response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
    response.setHeader("Access-Control-Allow-Origin", "*");

    response.setHeader("Vary", "Origin");

    
    
    // without this header jquery.ajax calls returns 401 even after successful login and SSESSIONID being succesfully stored.
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Expose-Headers", "location");


    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Authorization, Origin, Content-Type, Version");
    response.setHeader("Access-Control-Expose-Headers", "X-Requested-With, Authorization, Origin, Content-Type");

    if (!request.getMethod().equals("OPTIONS")) {
        chain.doFilter(req, res);
    } else {
        // do not continue with filter chain for options requests
    }
}

@Override
public void destroy() {

} 

@Override
public void init(FilterConfig filterConfig) throws ServletException {       
}
}













//@Configuration
//public class CORSFilter implements Filter {
//
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//		HttpServletResponse response = (HttpServletResponse) res;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//		response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
//
//		chain.doFilter(req, res);
//	}
//
//	public void init(FilterConfig filterConfig) {}
//
//	public void destroy() {}
//
//}