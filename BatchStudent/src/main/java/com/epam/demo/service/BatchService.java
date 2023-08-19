package com.epam.demo.service;

import java.util.List;

import com.epam.demo.dtos.BatchDTO;

public interface BatchService {
	List<BatchDTO> getAllBatches();
	BatchDTO getBatchWithId(int id);
	BatchDTO addBatch(BatchDTO batchDto);
	void deleteBatch(int id);
	BatchDTO updateBatch(BatchDTO batchDto);

}
