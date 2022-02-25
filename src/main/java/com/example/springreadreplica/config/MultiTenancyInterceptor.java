package com.example.springreadreplica.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Predicate;

public class MultiTenancyInterceptor implements HandlerInterceptor {

  Predicate<String> isGet = it -> it.equals("GET");
  Predicate<String> isReadOnlyPath = it -> it.equals("/read");

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    final String method = request.getMethod();
    final String requestURI = request.getRequestURI();
    if(isGet.test(method) && isReadOnlyPath.test(requestURI)) {
      request.setAttribute("TENANT_IDENTIFIER", "read");
    }
    return true;
  }
}
