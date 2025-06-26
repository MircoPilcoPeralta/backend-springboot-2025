package com.enterprise.project.demo1springboot.job.controller;

import com.enterprise.project.demo1springboot.job.model.entity.Job;
import com.enterprise.project.demo1springboot.job.service.IJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/job")
public class JobController {

    final private IJobService iJobService;

    public JobController(IJobService iJobService) {
        this.iJobService = iJobService;
    }

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody Job job) {
        Optional<Job> createdJobOptional = iJobService.createOrUpdateJob(job);

        if(createdJobOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el puesto, revise la información enviada");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdJobOptional.get());
    }

    @GetMapping
    public ResponseEntity<?> getAllJobs() {
        return ResponseEntity.status(HttpStatus.OK).body(iJobService.findAll());
    }

    // http://localhost:8080/api/job/545
    @GetMapping("{job-id}")
    public ResponseEntity<?> findJobById(@PathVariable("job-id") Long id){
        Optional<Job> foundJobOptional = iJobService.findJobById(id);

        if(foundJobOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                    String.format("No se pudo encontrar el job por id %d", id)
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(foundJobOptional.get());
    }

    //todo actualizar un job con varios empleados


    // todo crear un nuevo job pero con empleados registrados en la empresa


}
