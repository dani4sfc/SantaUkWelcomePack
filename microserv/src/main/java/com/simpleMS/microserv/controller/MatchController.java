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

//La clase controller debe llevar la anotación @RestController, la cual incluye @Controler + @ResponseBody
@RestController

//Con la anotación @Request Mapping, asociamos una url a un método, en este caso, /matches sería el padre, a partir de aqui, saldrían otros métodos /matches/damePartido...
@RequestMapping(value="/matches")
public class MatchController {
	
	//La clase logger la creamos a partir del import slf4j.Logger, sirve para personalizar la traza de mensajes y errores, mediante el application.yml y desde la propia clase en la que se inyecta
	 private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);
	//Aqui creamos el CRUD API REST
	
	//Instanciamos un objeto de service con @Autowired, donde tenemos definidos todos los métodos, para acceder a ellos
	@Autowired
	private MatchServiceImpl service;

	//Creamos el método READ, GET, para devolver un objeto dando por parámetro el id:
	
	//---------------------GET-----------------
	//Podemos hacerlo de 2 formas: con @RequestMapping o con @GetMapping, el GetMapping lo hace más sencillo
	
	/*@RequestMapping( method = RequestMethod.GET, params = { "matchId" })
	public MatchEntity readById() {
		return null;
	}*/

	/*
	 * ESTE método pasaría 2 parametros de entrada
	 * @GetMapping(path= "/{id}{id2}")
	public MatchEntity readById2(@PathVariable(value = "id") int id, @PathVariable(value = "id") int id2) {
		return null;
	}*/

	
	//Este método devuelve un Json de DTO cuyo id coincide con el puesto en API REST
	@GetMapping(path= "/{id}")
	public MatchDTO readById(@PathVariable(value = "id") int id) {
		//Para lanzar la función o método deseado (almacenados en service) ponemos la instancia de service y . para elegir el método:
		return service.findById(id);
				
	}
	
	//----------------GET ALL-------------
	@GetMapping(path= "/findAll")
	//Aquí simplemente creamos una lista de DTO's y devolvemos dicha lista
	public List<MatchDTO> readAll() {
		//Para lanzar la función o método deseado (almacenados en service) ponemos la instancia de service y . para elegir el método:
		return service.findAll();
				
	}
	
	//------------------------CREATE-----------------
	//crea bien los registros en la BBDD, pero al devolverlo lee el DTO sin el id autoincrementado, ya que este se asigna en la BBDD
	//Tengo que buscar una forma de asignarle al dto el id, leyendo eol último registro
	@PostMapping(path="/createMatches", consumes= "application/json")
	public MatchDTO create(@RequestBody MatchDTO dto) {
		
		String result;
		return service.create(dto);
		
	}
	//Cuando creamos un partido, definimos un ganador, hay que comprobar el ganador y darle puntos
	
	
	//------------------------UPDATE-----------------
	
	//IMPORTANTE siempre que hagamos una escritura en BBDD, debemos añadir @RequestBody en el parámetro de entrada.
	@PutMapping(path="/updateMatches", consumes="application/json")
	//@RequestBody siempre antes del parametro de entrada
	public MatchDTO updateMatch(@RequestBody MatchDTO input){
		
		service.updateMatch(input);
		
		return input;
	}
	
	
	//Para usar patch (Modificar solo algunos campos), tengo que crear un método nuevo en service.
	/*@PatchMapping(path="/updateMatches2", consumes="application/json")
	//@RequestBody siempre antes del parametro de entrada
	public MatchDTO updateMatch2(@RequestBody MatchDTO input){
		
		service.updateMatch(input);
		
		return input;
	}*/
	
	//-----------------------DELETE------------------
	
	//Para el delete, ponemos la anotación @DeleteMapping y el path de acceso+id
	@DeleteMapping(path="/deleteMatches/{id}")
	public void delete(@PathVariable(value = "id") int id) {

		//if (service.findById(id)!= null) {
			service.deleteMatchById(id);

		//}else {
			
		//}
	}
	
	@GetMapping(path= "/")
	public List<MatchDTO> findByResultLocalVisitante(@RequestParam String name, @RequestParam(required = false) String isLocal) {
		//Para lanzar la función o método deseado (almacenados en service) ponemos la instancia de service y . para elegir el método:
		
		
		//Condición para que devuelva una query u otra, en función del parametro isLocal
		if (isLocal.contentEquals("Local")) {
		return service.findByResultLocal(name, isLocal);
				
		}else if (isLocal.contentEquals("Visitante")) {
			
			return service.findByResultVisitante(name, isLocal);
			
		}else {
		return null; 
		}

	}
	


}
