package com.example.demo.repository;

import com.example.demo.entity.UserTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTb,Long> {
}
