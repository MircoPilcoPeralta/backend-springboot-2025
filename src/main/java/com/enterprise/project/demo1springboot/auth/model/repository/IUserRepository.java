package com.enterprise.project.demo1springboot.auth.model.repository;

import com.enterprise.project.demo1springboot.auth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(final String email);
}
