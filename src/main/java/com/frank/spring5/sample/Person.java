package com.frank.spring5.sample;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;



@Document
public class Person {

	private @Id String id;
	private final String firstname;
	private final String lastname;
	private final int age;
	
	@JsonCreator
	public Person(@JsonProperty("id") String id, @JsonProperty("firstname") String firstname, 
			@JsonProperty("lastname") String lastname, @JsonProperty("age") int age) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public int getAge() {
		return age;
	}
	
	
}
