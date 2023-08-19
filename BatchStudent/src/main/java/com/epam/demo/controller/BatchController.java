package com.epam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.demo.dtos.BatchDTO;
import com.epam.demo.service.BatchServiceImpl;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/batches")
public class BatchController {
	@Autowired
	BatchServiceImpl batchService;
	
	@GetMapping("{id}")
	ResponseEntity<BatchDTO> getBatch(@PathVariable int id){
		return new ResponseEntity<>(batchService.getBatchWithId(id),HttpStatus.OK);
	}
	@GetMapping()
	ResponseEntity<List<BatchDTO>> getAllBatches(){
		return new ResponseEntity<>(batchService.getAllBatches(),HttpStatus.OK);
	}
	@PostMapping()
	ResponseEntity<BatchDTO> addBatch(@RequestBody @Valid BatchDTO batchDto){
		return new ResponseEntity<>(batchService.addBatch(batchDto),HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteBatch(@PathVariable int id) {
		batchService.deleteBatch(id);
	}

	@PutMapping()
	ResponseEntity<BatchDTO> updateBatch(@RequestBody @Valid BatchDTO batchDto){
		return new ResponseEntity<>(batchService.updateBatch(batchDto),HttpStatus.OK);
	}

}
