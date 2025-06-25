package com.enterprise.project.demo1springboot.employee.controller;

import com.enterprise.project.demo1springboot.employee.model.entity.Employee;
import com.enterprise.project.demo1springboot.employee.service.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class Controller {

    final IEmployeeService iEmployeeService;

    public Controller(IEmployeeService iEmployeeService) {
        this.iEmployeeService = iEmployeeService;
    }


    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(iEmployeeService.findAll());
    }

    @PostMapping()
    public ResponseEntity<?> createOrUpdate(@RequestBody @Valid Employee employee) {
        Optional<Employee> employeeOptional = iEmployeeService.createOrUpdateEmployee(employee);
        if(employeeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el empleado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeOptional.get());
    }

    @PostMapping("/generate-report")
    public ResponseEntity<?> sendEmployeesReport(@RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body("Email enviado exitosamente");
    }


    // todo crear una lista páginada de empleados


    // todo crear un buscador de empleados por query params

}
