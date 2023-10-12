package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Person;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.exception.InvalidNameFormatException;

public interface PersonService {
	Person addPerson(Person person) throws InvalidNameFormatException;
	
	void deletePerson(int id) throws IdNotFoundException;
	
	Person updatePersonName(int id ,String name) throws InvalidNameFormatException,IdNotFoundException;
	
	List<Person> getPersons();
	
	Page<Person> getPersonsBySort(int offset,int limit,String sortby);
	
	List<Person> getPersonsByLimit(int offset,int limit);

}
