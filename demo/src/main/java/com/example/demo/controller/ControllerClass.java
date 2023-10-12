package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Person;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.exception.InvalidNameFormatException;
import com.example.demo.service.PersonService;

@RestController
public class ControllerClass {

	@Autowired
	private PersonService personService;

	@PostMapping("/addPerson")
	public Person addPerson(Person person) throws InvalidNameFormatException {
		return personService.addPerson(person);
	}

	@DeleteMapping("/deletePeron/{id}")
	public String deletePerson(@PathVariable("id") int id) throws IdNotFoundException {
		personService.deletePerson(id);
		return id+" data deleted";
	}

	@PutMapping("/updateName/{id}")
	public Person updatePersonName(@PathVariable("id")int  id,@RequestParam("name") String name) throws InvalidNameFormatException,IdNotFoundException {
		return personService.updatePersonName(id, name);
	}

	@GetMapping("/persons")
	public List<Person> getPersons() {
		return personService.getPersons();
	}

	@RequestMapping(value="/getPersonByLimit/{offset}/{limit}",method=RequestMethod.GET)
	public List<Person> getPersonByLimit(@PathVariable int offset,@PathVariable int limit){
		return personService.getPersonsByLimit(offset, limit);
	}
	
	@RequestMapping(value="/getPersonBySort/{offset}/{limit}/{sortBy}",method=RequestMethod.GET)
	public Page<Person> getPersonBySort(@PathVariable int offset,int limit,String sortBy){
		return personService.getPersonsBySort(offset, limit, sortBy);
	}
}
