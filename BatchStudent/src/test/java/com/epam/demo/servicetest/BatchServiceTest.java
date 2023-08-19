package com.epam.demo.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.demo.dtos.BatchDTO;
import com.epam.demo.dtos.StudentDTO;
import com.epam.demo.entities.Batch;
import com.epam.demo.entities.Student;
import com.epam.demo.exceptions.BatchException;
import com.epam.demo.repository.BatchRepo;
import com.epam.demo.service.BatchServiceImpl;

@ExtendWith(MockitoExtension.class)
class BatchServiceTest {

	@InjectMocks
	BatchServiceImpl batchService;
	@Mock
	ModelMapper mapper;
	@Mock
	BatchRepo batchRepo;
	private BatchDTO batchDto;
	private StudentDTO studentDto;
	private Batch batch;
	private Student student;

	@BeforeEach
	void setup() {
		student = new Student();
		student.setId(1);
		student.setName("bhav");
		student.setAge(14);
		student.setBatch(batch);
		studentDto = new StudentDTO();
		studentDto.setId(1);
		studentDto.setName("bhav");
		studentDto.setAge(14);
		batch = new Batch();
		batch.setId(1);
		batch.setName("RD");
		batch.setDomain("Java");
		batch.setStudents(List.of(student));
		batchDto = new BatchDTO();
		batchDto.setId(1);
		batchDto.setName("RD");
		batchDto.setDomain("Java");
//		batchDto.setStudents(List.of(student));
	}

	@Test
	void getBatchTest() {
		Mockito.when(batchRepo.findById(1)).thenReturn(Optional.of(batch));
		Mockito.when(mapper.map(batch, BatchDTO.class)).thenReturn(batchDto);
		assertEquals(batchDto, batchService.getBatchWithId(1));
	}

	@Test
	void getAllBatches() {
		List<BatchDTO> list = new ArrayList<>();
		list.add(batchDto);
		List<Batch> batchList = new ArrayList<>();
		batchList.add(batch);
		Mockito.when(batchRepo.findAll()).thenReturn(batchList);
		Mockito.when(mapper.map(batch, BatchDTO.class)).thenReturn(batchDto);
		assertEquals(list, batchService.getAllBatches());
	}

	@Test
	void testInvalidIdGet() {
		Mockito.when(batchRepo.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(BatchException.class, () -> batchService.getBatchWithId(23));
	}

	@Test
	void testBatchAddition() {
		Mockito.when(mapper.map(batchDto, Batch.class)).thenReturn(batch);
		Mockito.when(batchRepo.save(batch)).thenReturn(batch);
		Mockito.when(mapper.map(batch, BatchDTO.class)).thenReturn(batchDto);
		assertEquals(batchDto, batchService.addBatch(batchDto));
	}

	@Test
	void deleteTest() {
		Mockito.doNothing().when(batchRepo).deleteById(1);
		batchService.deleteBatch(1);
	}

	@Test
	void updateTest() {
		Mockito.when(batchRepo.existsById(anyInt())).thenReturn(true);
		Mockito.when(mapper.map(batchDto, Batch.class)).thenReturn(batch);
		Mockito.when(batchRepo.save(batch)).thenReturn(batch);
		assertEquals(batchDto, batchService.updateBatch(batchDto));
	}

	@Test
	void getStudent() {
		Batch b = new Batch(1, "batch", "python", List.of(student));
		BatchDTO bdto = new BatchDTO(1, "batch", "python");
		Student s = new Student("bha", 14);
		StudentDTO sdto = new StudentDTO(1, "bha", 14);
		s.setId(1);
		assertEquals(1, sdto.getId());
		assertEquals("bha", sdto.getName());
		assertEquals(14, sdto.getAge());
		assertEquals("bha", s.getName());
		assertEquals(14, s.getAge());
		assertEquals(batch, student.getBatch());
	}

	@Test
	void updateTestWithInvalidId() {
		Mockito.when(batchRepo.existsById(anyInt())).thenReturn(false);
		assertThrows(BatchException.class, () -> batchService.updateBatch(batchDto));
	}
}
