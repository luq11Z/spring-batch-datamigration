package com.lucaslearning.datamigration.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String email;
	private LocalDate birthDate;
	private Integer age;

	public Person() {

	}

	public Person(Integer id, String name, String email, LocalDate birthDate, Integer age) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isValid() {
		return (name != null && !name.isBlank()) && (email != null && !email.isBlank()) && birthDate != null;
	}

}
