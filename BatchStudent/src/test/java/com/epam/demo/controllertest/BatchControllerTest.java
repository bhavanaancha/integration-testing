package com.epam.demo.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.demo.controller.BatchController;
import com.epam.demo.dtos.BatchDTO;
import com.epam.demo.dtos.StudentDTO;
import com.epam.demo.entities.Batch;
import com.epam.demo.entities.Student;
import com.epam.demo.exceptions.BatchException;
import com.epam.demo.service.BatchServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BatchController.class)
 class BatchControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	BatchServiceImpl batchService;
	private BatchDTO batchDto;
	private StudentDTO studentDto;
	private Batch batch;
	private Student student;
	
	@BeforeEach
	void setup() {
		 student=new Student();
		student.setId(1);
		student.setName("bhav");
		student.setAge(14);
		 studentDto=new StudentDTO();
		studentDto.setId(1);
		studentDto.setName("bhav");
		studentDto.setAge(14);
		 batch=new Batch();
		batch.setId(1);
		batch.setName("RD");
		batch.setDomain("Java");
		batch.setStudents(List.of(student));
		 batchDto=new BatchDTO();
		batchDto.setId(1);
		batchDto.setName("RD");
		batchDto.setDomain("Java");
//		batchDto.setStudents(List.of(student));
	}
	
	@Test
	void testGetBatchWithId() throws Exception{
		Mockito.when(batchService.getBatchWithId(1)).thenReturn(batchDto);
		mockMvc.perform(get("/batches/1")).andExpect(status().isOk());
	}
	@Test
	void testGetBatchWithInvalidExId() throws Exception{
		Mockito.when(batchService.getBatchWithId(1)).thenThrow(BatchException.class);
		mockMvc.perform(get("/batches/1")).andExpect(status().isNotFound());
	}
	@Test
	void testGetBatches() throws Exception{
		List<BatchDTO> batchList=new ArrayList<>();
		batchList.add(batchDto);
		Mockito.when(batchService.getAllBatches()).thenReturn(batchList);
		mockMvc.perform(get("/batches")).andExpect(status().isOk());
	}
	@Test
	void testAddBatch() throws Exception{
		Mockito.when(batchService.addBatch(batchDto)).thenReturn(batchDto);
		mockMvc.perform(post("/batches").contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(batch))).andExpect(status().isOk());
	}
	@Test
	void testAddExceptionBatch() throws Exception{
		BatchDTO dto=new BatchDTO();
		dto.setId(1);
		Mockito.when(batchService.addBatch(dto)).thenReturn(dto);
		mockMvc.perform(post("/batches").contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(dto))).andExpect(status().isBadRequest());
	}
	@Test
	void testDeleteBatch() throws Exception{
		Mockito.doNothing().when(batchService).deleteBatch(1);
		mockMvc.perform(delete("/batches/1")).andExpect(status().isNoContent());
	}
	@Test
	void testUpdateBatch() throws Exception{
		Mockito.when(batchService.updateBatch(batchDto)).thenReturn(batchDto);
		mockMvc.perform(put("/batches").contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(batch))).andExpect(status().isOk());
	}
	@Test
	void testGetBatchWithInvalidId() throws Exception{
		Mockito.when(batchService.getBatchWithId(1)).thenThrow(BatchException.class);
		mockMvc.perform(get("/batches/ju")).andExpect(status().isInternalServerError());
	}
}
