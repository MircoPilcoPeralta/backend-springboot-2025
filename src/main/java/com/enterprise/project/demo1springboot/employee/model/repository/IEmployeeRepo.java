package com.enterprise.project.demo1springboot.employee.model.repository;

import com.enterprise.project.demo1springboot.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("SELECT empl FROM Employee empl")
    List<Employee> findAllEmployeesRegistered();
}
