package com.example.springreadreplica.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Optional;

@Component
public class DefaultCurrentTenantIdentifierResolver
    implements CurrentTenantIdentifierResolver {

  @Override
  public String resolveCurrentTenantIdentifier() {

    return Optional
        .ofNullable(RequestContextHolder.getRequestAttributes())
        .map(it -> it.getAttribute("TENANT_IDENTIFIER", RequestAttributes.SCOPE_REQUEST))
        .map(String.class::cast)
        .orElse("master");
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }
}
