package com.enterprise.project.demo1springboot.job.model.repository;

import com.enterprise.project.demo1springboot.job.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobRepository extends JpaRepository<Job, Long> {
}
