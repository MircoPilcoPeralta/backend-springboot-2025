package com.enterprise.project.demo1springboot.employee.controller;

import com.enterprise.project.demo1springboot.employee.model.Request.CreateEmployeeRecRequest;
import com.enterprise.project.demo1springboot.employee.model.Request.CreateEmployeeRequest;
import com.enterprise.project.demo1springboot.employee.model.Request.CreateEmployeeWithJobRequest;
import com.enterprise.project.demo1springboot.employee.model.Response.EmployeeResponse;
import com.enterprise.project.demo1springboot.employee.model.entity.Employee;
import com.enterprise.project.demo1springboot.employee.service.IEmployeeService;
import com.enterprise.project.demo1springboot.shared.model.response.StandardResponse;
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
    public ResponseEntity<StandardResponse> createOrUpdate(@RequestBody @Valid CreateEmployeeWithJobRequest employeeRecRequest) {
        final Employee newEmployee = new Employee();
        newEmployee.setFullname(employeeRecRequest.fullname());
        newEmployee.setEmail(employeeRecRequest.email());
        newEmployee.setBirthDate(employeeRecRequest.birthDate());
        newEmployee.setJob(employeeRecRequest.job());

        Optional<Employee> employeeOptional = iEmployeeService.createOrUpdateEmployee(newEmployee);

        if(employeeOptional.isEmpty()) {
            StandardResponse standardResponse = new StandardResponse();
            standardResponse.setMessage("No se pudo crear el empleado");
            standardResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardResponse);
        }

        Employee employee = employeeOptional.get();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setFullname(employee.getFullname());
        employeeResponse.setJob(employee.getJob());

        StandardResponse standardResponse = new StandardResponse();
        standardResponse.setMessage("Empleado creado exitosamente!");
        standardResponse.setStatusCode(HttpStatus.CREATED.toString());
        standardResponse.setData(employeeResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(standardResponse);
    }

    @GetMapping("/employees-jpql")
    public ResponseEntity<?> getEmployeesUsingJPQL() {
        return ResponseEntity.status(HttpStatus.OK).body(iEmployeeService.findAllUsingJPQL());
    }

    //http://localhost:8080/api/employee/generate-report?email=prueba@email.com&hour=15
    @PostMapping("/generate-report")
    public ResponseEntity<?> sendEmployeesReport(@RequestParam("email") String email, @RequestParam("hour") Integer hour ) {
        return ResponseEntity.status(HttpStatus.OK).body("Email enviado exitosamente");
    }


    // todo crear una lista páginada de empleados


    // todo crear un buscador de empleados por query params

}
