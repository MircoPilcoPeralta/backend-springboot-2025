package com.enterprise.project.demo1springboot.employee.model.Request;

import jakarta.validation.constraints.Email;

import java.util.Date;

// DTO
// Request
// POJO
public class CreateEmployeeRequest {

    private String fullname;

    @Email(message = "Debes ingresar un email en formato de email, por ejemplo: @gmail.com")
    private String email;

    private Date birthDate;

    public CreateEmployeeRequest() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public @Email(message = "Debes ingresar un email en formato de email, por ejemplo: @gmail.com") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Debes ingresar un email en formato de email, por ejemplo: @gmail.com") String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
