package com.epam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.demo.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

}
