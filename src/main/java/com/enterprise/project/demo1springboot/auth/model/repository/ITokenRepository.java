package com.enterprise.project.demo1springboot.auth.model.repository;

import com.enterprise.project.demo1springboot.auth.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepository extends JpaRepository<Token, Long> {
}
