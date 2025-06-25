package com.enterprise.project.demo1springboot.employee.service;

import com.enterprise.project.demo1springboot.employee.model.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    /**
     * Method to find all employees
     * @return list of all saved employees
     * */
    List<Employee> findAll();

    Optional<Employee> findEmployeeById(final Long id);

    Optional<Employee> createOrUpdateEmployee(final Employee employee);

}
