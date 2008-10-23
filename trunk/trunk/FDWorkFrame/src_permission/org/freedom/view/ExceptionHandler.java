package org.freedom.view;

import java.lang.reflect.Method;

import javax.servlet.ServletException;

import org.springframework.aop.ThrowsAdvice;

public class ExceptionHandler implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, ServletException ex) {

    }

}
