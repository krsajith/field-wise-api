package com.unnathy.fieldwise.core;

import com.unnathy.fieldwise.user.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemFieldAspect {

    @Before("execution(* org.springframework.data.repository.CrudRepository+.save(..))")
    public void beforeSave(JoinPoint joinPoint) {
        if (joinPoint.getArgs().length == 0) {
            return;
        }
        Object entity = joinPoint.getArgs()[0];
        setUserIdIfMissing(entity);
    }

    @Before("execution(* org.springframework.data.repository.CrudRepository+.saveAll(..))")
    public void beforeSaveAll(JoinPoint joinPoint) {
        if (joinPoint.getArgs().length == 0) {
            return;
        }
        Object arg = joinPoint.getArgs()[0];
        if (!(arg instanceof Iterable<?> iterable)) {
            return;
        }
        for (Object entity : iterable) {
            setUserIdIfMissing(entity);
        }
    }

    private void setUserIdIfMissing(Object entity) {
        if (entity == null) {
            return;
        }
        Long principalUserId = getAuthenticatedUserId();
        if (principalUserId == null) {
            return;
        }

        BeanWrapper wrapper = new BeanWrapperImpl(entity);
        if (!wrapper.isWritableProperty("userId")) {
            return;
        }
        Class<?> propertyType = wrapper.getPropertyType("userId");
        if (propertyType != null && !Long.class.equals(propertyType)) {
            return;
        }
        Object currentValue = wrapper.getPropertyValue("userId");
        if (currentValue != null) {
            return;
        }
        wrapper.setPropertyValue("userId", principalUserId);
    }

    private Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof User user) {
            return user.getId();
        }
        return null;
    }
}
