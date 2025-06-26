package com.enterprise.project.demo1springboot.employee.model.Request;

import jakarta.validation.constraints.Email;

import java.util.Date;

public record CreateEmployeeRecRequest(
        String fullname,
        @Email(message = "El email no ingresado no contempla el formato estandar..")  String email,
        Date birthDate
) {
}
