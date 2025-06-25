package com.enterprise.project.demo1springboot.employee.model.repository;

import com.enterprise.project.demo1springboot.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Long> {

}
