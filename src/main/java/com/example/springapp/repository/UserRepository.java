package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapp.entity.User;
//import com.example.springapp.springapp.controller.UserController;
public interface UserRepository extends JpaRepository<User, Long> {
}