package com.enterprise.project.demo1springboot.employee.service;

import com.enterprise.project.demo1springboot.employee.model.entity.Employee;
import com.enterprise.project.demo1springboot.employee.model.repository.IEmployeeRepo;
import com.enterprise.project.demo1springboot.job.model.repository.IJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    // Inyección de dependencias
    final IEmployeeRepo employeeRepo;
    final IJobRepository jobRepository;

    public EmployeeServiceImpl(IEmployeeRepo employeeRepo, IJobRepository jobRepository) {
        this.employeeRepo = employeeRepo;
        this.jobRepository = jobRepository;
    }
    // Inyección de dependencias


    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    @Override
    public Optional<Employee> createOrUpdateEmployee(Employee employee) {
        Employee savedEmployee = employeeRepo.save(employee);
        return Optional.of(savedEmployee);
    }

    @Override
    public List<Employee> findAllUsingJPQL() {
        return employeeRepo.findAllEmployeesRegistered();
    }
}
