package com.enterprise.project.demo1springboot.employee.model.entity;

import com.enterprise.project.demo1springboot.job.model.entity.Job;
import com.enterprise.project.demo1springboot.project.model.entity.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
@Builder
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    @Email(message = "Debes ingresar un email en formato de email, por ejemplo: @gmail.com")
    private String email;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_job")
    @JsonIgnore
    private Job job;

    @ManyToMany
    @JoinTable(
            name = "project_employee",
            joinColumns = @JoinColumn(name = "id_employee"),
            inverseJoinColumns = @JoinColumn(name = "id_project")
    )
    @JsonIgnore
    private List<Project> projects;

    public Employee() {
    }

    public Employee(String fullname, String email, Date birthDate) {
        this.fullname = fullname;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Employee(Long id, String fullname, String email, Date birthDate, Job job, List<Project> projects) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.birthDate = birthDate;
        this.job = job;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public @Email(message = "Debes ingresar un email en formato de email, por ejemplo: @gmail.com") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Debes ingresar un email en formato de email, por ejemplo: @gmail.com") String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
