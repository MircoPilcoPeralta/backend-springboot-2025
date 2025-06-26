package com.enterprise.project.demo1springboot.employee.service.exception;


/**
 * Excepción que se dispara cuando
 */
public class JobAssignationException extends Exception {
    public JobAssignationException(String message) {
        super(message);
    }
}
