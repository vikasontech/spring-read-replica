package com.example.springreadreplica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MultiTenancyInterceptor  extends HandlerInterceptorAdapter {

  List<String> lists = new ArrayList<>();

  Predicate<String> isGet = it -> it.equals("GET");
//  Predicate<String> isReadOnlyPath = it -> lists.contains(it);
  Predicate<String> isReadOnlyPath = it -> it.equals("/hello2");

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    final String method = request.getMethod();
    final String requestURI = request.getRequestURI();
    Map<String, Object> pathVars = (Map<String, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    if(isGet.test(method) && isReadOnlyPath.test(requestURI)) {
//      ServletRequestAttributes attributes = new ServletRequestAttributes(request);
//      request.setAttribute("CURRENT_TENANT_IDENTIFIER", pathVars.get("read"));
//      RequestContextHolder.setRequestAttributes(attributes);

      request.setAttribute("CURRENT_TENANT_IDENTIFIER", "read");

    }
    return true;
  }
}
