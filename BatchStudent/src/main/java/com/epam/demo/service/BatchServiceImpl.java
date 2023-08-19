package com.epam.demo.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.demo.dtos.BatchDTO;
import com.epam.demo.entities.Batch;
import com.epam.demo.exceptions.BatchException;
import com.epam.demo.repository.BatchRepo;

@Service
public class BatchServiceImpl implements BatchService{
	@Autowired
	ModelMapper mapper;
	@Autowired
	BatchRepo batchRepo;

	@Override
	public List<BatchDTO> getAllBatches() {
		
		return batchRepo.findAll().stream().map(batch->mapper.map(batch,BatchDTO.class)).toList();
	}

	@Override
	public BatchDTO getBatchWithId(int id) throws BatchException {
		
		return batchRepo.findById(id).map(batch->mapper.map(batch, BatchDTO.class))
				.orElseThrow(()->new BatchException("batch with this id can't be found"));
	}

	@Override
	public BatchDTO addBatch(BatchDTO batchDto) {
		Batch batch=batchRepo.save(mapper.map(batchDto, Batch.class));
		return mapper.map(batch, BatchDTO.class);
	}

	@Override
	public void deleteBatch(int id) {
		batchRepo.deleteById(id);
		
	}

	@Override
	public BatchDTO updateBatch(BatchDTO batchDto) {
		if(batchRepo.existsById(batchDto.getId())) {
			batchRepo.save(mapper.map(batchDto, Batch.class));
			return batchDto;
		}
		else {
			throw new BatchException("batch with this id doesn't exists");
		}
	}

}
