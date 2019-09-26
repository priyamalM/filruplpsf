package com.slt.documentmanagment.repository;

import com.slt.documentmanagment.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String name);
    Page<User> findByUsernameContaining(String name, Pageable pageable);
}