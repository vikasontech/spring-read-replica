package com.example.springreadreplica.config.web.interceptors;

import com.example.springreadreplica.config.AppConstants;
import com.mysql.cj.Constants;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MultiTenanceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        final String method = request.getMethod();
        final String requestURI = request.getRequestURI();

        if(method.equals("GET") && requestURI.equals("/" + AppConstants.REPLICA)) {
            request.setAttribute("TENANT_IDENTIFIER", AppConstants.REPLICA);
        }

        return true;
    }
}
