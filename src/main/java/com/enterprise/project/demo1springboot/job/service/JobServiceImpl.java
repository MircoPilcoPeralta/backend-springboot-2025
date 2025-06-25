package com.enterprise.project.demo1springboot.job.service;

import com.enterprise.project.demo1springboot.job.model.entity.Job;
import com.enterprise.project.demo1springboot.job.model.repository.IJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements IJobService {

    final private IJobRepository iJobRepository;

    public JobServiceImpl(IJobRepository iJobRepository) {
        this.iJobRepository = iJobRepository;
    }

    @Override
    public List<Job> findAll() {
        return iJobRepository.findAll();
    }

    @Override
    public Optional<Job> createOrUpdateJob(Job job) {
        return Optional.of(iJobRepository.save(job));
    }

    @Override
    public Optional<Job> findJobById(Long id) {
        return iJobRepository.findById(id);
    }

    @Override
    public Boolean deleteJobById(Long id) {
        final Optional<Job> jobOptional = iJobRepository.findById(id);

        if(jobOptional.isEmpty()) {
            return false;
        }

        iJobRepository.delete(jobOptional.get());
        return true;
    }
}
