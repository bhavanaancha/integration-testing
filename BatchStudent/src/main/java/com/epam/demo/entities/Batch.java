package com.epam.demo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String domain;
	
	@OneToMany(mappedBy="batch",cascade=CascadeType.ALL, orphanRemoval=true)
	List<Student> students;
	
	public void setStudents(List<Student> students) {
		students.forEach(a->a.setBatch(this));
		this.students=students;
	}

}
