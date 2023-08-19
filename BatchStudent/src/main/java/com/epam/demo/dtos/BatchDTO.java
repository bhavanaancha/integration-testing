package com.epam.demo.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {
	private int id;
	@NotBlank(message="name cannot be null")
	private String name;
	@NotBlank(message="name cannot be null")
	private String domain;
	
//	private List<StudentDTO> students;

}
