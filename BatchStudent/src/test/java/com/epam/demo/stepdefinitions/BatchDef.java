package com.epam.demo.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import com.epam.demo.dtos.BatchDTO;

import io.cucumber.spring.CucumberContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BatchDef {

	BatchDTO batchDTO;

	@LocalServerPort
	private int port;
	private ResponseEntity<BatchDTO> responseEntity;
	private ResponseEntity<BatchDTO> responseEntity2;
	int id;
	RestTemplate restTemplate = new RestTemplate();
	private String baseUrl = "http://localhost:";

	@Given("batch {int}")
	public void batchId(int id) {
		this.id = id;
		baseUrl = baseUrl.concat(port + "").concat("/batches");
	}

	@When("requested to find batch")
	public void requestedToFindBatch() {

		responseEntity = restTemplate.getForEntity(baseUrl+"/"+id, BatchDTO.class);
	}

	@Then("the status code should be {int}")
	public void giveBatchInfo(int expectedStatus) {
		assertEquals(expectedStatus, responseEntity.getStatusCode().value());
	}
}
