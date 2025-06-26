package com.enterprise.project.demo1springboot.employee.model.Request;

import com.enterprise.project.demo1springboot.job.model.entity.Job;
import jakarta.validation.constraints.Email;

import java.util.Date;

public record CreateEmployeeWithJobRequest(
        String fullname,
        @Email(message = "El email no ingresado no contempla el formato estandar..")  String email,
        Date birthDate,
        Job job
) {
}
