package com.qualsure.dataapi.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;




//This class will be responsible to send response when the credentials are no longer authorized.
//If the authentication event was successful, or authentication was not attempted because the HTTP header did not contain a supported authentication request, 
//the filter chain will continue as normal. 
//The only time the filter chain will be interrupted is 
//if authentication fails and the AuthenticationEntryPoint is called.






@SuppressWarnings("serial")
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}