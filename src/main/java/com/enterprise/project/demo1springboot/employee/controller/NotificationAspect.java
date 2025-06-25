package com.enterprise.project.demo1springboot.employee.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Aspect
@Component
public class NotificationAspect {

    private static final Logger logger = LoggerFactory.getLogger(NotificationAspect.class);

    @Around("execution(* com.enterprise.project.demo1springboot.employee.controller.Controller.sendEmployeesReport(..))")
    public Object handleControllerErrors(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.error(" Provee el servicio de gmail y funciona correctamente!!!");
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            logger.error("Algo salió mal en sendEmployeesReport", e);
            return ResponseEntity.badRequest().body("Petición inválida: " + e.getMessage());
        }
    }
}

