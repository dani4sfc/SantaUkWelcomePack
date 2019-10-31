package com.simpleMS.microserv.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simpleMS.microserv.dto.MatchDTO;
import com.simpleMS.microserv.service.MatchServiceImpl;

//The Controller class should has the annotation @RestController, which includes  @Controler + @ResponseBody
@RestController

//With the annotation @Request Mapping, we link an url to a method, in this case, /matches it's the father, from there, there will pend other methods: /matches/damePartido...
@RequestMapping(value="/matches")
public class MatchController {
	
	//The logger class that we are creating it's from the import slf4j.Logger, It's for printing messages and errors while executing the code, with the application.yml and by the class where it's inyected
	 private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);
	//Here we create the CRUD API REST
	
	//We instance and Service object with @Autowired, where we have all the methods defined, in order to access them
	@Autowired
	private MatchServiceImpl service;

	//We create READ, GET, in order to return an object by a given id:
	
	//---------------------GET-----------------
	//We can do it on 2 ways: with @RequestMapping or with @GetMapping, the GetMapping make it easier
	
	/*@RequestMapping( method = RequestMethod.GET, params = { "matchId" })
	public MatchEntity readById() {
		return null;
	}*/

	/*
	 * THis method receives 2 parameters
	 * @GetMapping(path= "/{id}{id2}")
	public MatchEntity readById2(@PathVariable(value = "id") int id, @PathVariable(value = "id") int id2) {
		return null;
	}*/

	
	//THis method returns a DTO Json, which id matches with the id we set on the API REST
	@GetMapping(path= "/{id}")
	public MatchDTO readById(@PathVariable(value = "id") int id) {
		//In order to execute a method/function (stored in service) we set an instance of service and . for choose the method:
		return service.findById(id);
				
	}
	
	//----------------GET ALL-------------
	@GetMapping(path= "/findAll")
	//Here we simply create a list of DTO's and return the list.
	public List<MatchDTO> readAll() {
		//To launch the desired function or method (stored in service) we put the instance of service and . to choose the method:
		return service.findAll();
				
	}
	
	//------------------------CREATE-----------------
	//creates well the records in the DDBB, but when it is returned it reads the DTO without the autoincremented id, since this is assigned in the DDBB.
	@PostMapping(path="/createMatches", consumes= "application/json")
	public MatchDTO create(@RequestBody MatchDTO dto) {
		
		String result;
		return service.create(dto);
		
	}
	//When we create a match, we define a winner, we have to check the winner and give him points.
	
	
	//------------------------UPDATE-----------------
	
	//IMPORTANT whenever we make a writing in BBDD, we must add @RequestBody in the input parameter.
	@PutMapping(path="/updateMatches", consumes="application/json")
	//@RequestBody always before the input parameter
	public MatchDTO updateMatch(@RequestBody MatchDTO input){
		
		service.updateMatch(input);
		
		return input;
	}
	
	
	//To use patch (Modify only some fields), I have to create a new method in service.
	/*@PatchMapping(path="/updateMatches2", consumes="application/json")
	//@RequestBody always before the input parameter
	public MatchDTO updateMatch2(@RequestBody MatchDTO input){
		
		service.updateMatch(input);
		
		return input;
	}*/
	
	//-----------------------DELETE------------------
	
	//For the delete, we put the annotation @DeleteMapping and the access path+id
	@DeleteMapping(path="/deleteMatches/{id}")
	public void delete(@PathVariable(value = "id") int id) {

		//if (service.findById(id)!= null) {
			service.deleteMatchById(id);

		//}else {
			
		//}
	}
	
	@GetMapping(path= "/")
	public List<MatchDTO> findByResultLocalVisitante(@RequestParam String name, @RequestParam(required = false) String isLocal) {
		//To launch the desired function or method (stored in service) we put the instance of service and . to choose the method:		
		
		//Condition to return one query or another, depending on the isLocal parameter
		if (isLocal.contentEquals("Local")) {
		return service.findByResultLocal(name, isLocal);
				
		}else if (isLocal.contentEquals("Visitante")) {
			
			return service.findByResultVisitante(name, isLocal);
			
		}else {
		return null; 
		}

	}
	


}
