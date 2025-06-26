package com.enterprise.project.demo1springboot.employee.model.Response;

import com.enterprise.project.demo1springboot.job.model.entity.Job;

//VO
public class EmployeeResponse {
    private String fullname;
    private Job job;

    public EmployeeResponse() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
