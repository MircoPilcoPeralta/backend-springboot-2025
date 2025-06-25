package com.enterprise.project.demo1springboot.job.service;

import com.enterprise.project.demo1springboot.job.model.entity.Job;

import java.util.List;
import java.util.Optional;

public interface IJobService {

    List<Job> findAll();

    Optional<Job> createOrUpdateJob(final Job job);

    Optional<Job> findJobById(final Long id);

    Boolean deleteJobById(final Long id);



}
