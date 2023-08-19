package com.epam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.demo.entities.Batch;

public interface BatchRepo extends JpaRepository<Batch, Integer>{

}
