package com.simpleMS.microserv.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simpleMS.microserv.dto.TeamDTO;
import com.simpleMS.microserv.service.TeamServiceImpl;



@RestController
@RequestMapping(value="/teams")
public class TeamController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

	 @Autowired 
	 private TeamServiceImpl service;
	 
	
	 //-------------GET----------
 
	 @GetMapping(path="/{id}")
	 public TeamDTO readById(@PathVariable(value = "id")int id) {
		 return service.findTeamById(id);
	 }
	 
	 
	 //-----------GETALL----------
	 @GetMapping(path="/findAll")
	 public List<TeamDTO> readAll(){
		 return service.findAll();
	
	 }
	//--------------CREATE------------	 
	@PostMapping(path="/createTeams", consumes="application/json")
	public TeamDTO create(@RequestBody TeamDTO dto) {
		return service.create(dto);
	}
		 
	 
	//--------------UPDATE-------------
	@PutMapping(path="/updateTeams", consumes="application/json")
	public TeamDTO updateTeams(@RequestBody TeamDTO input) {
		return service.updateTeam(input);
	}
//	//Establecer puntos
	@PatchMapping(path="/updatePoints/{id}", consumes="application/json")
	public TeamDTO updateTeams(@PathVariable Integer id, @RequestParam Integer points) {
		return service.updatePoints(id, points);
	}
	
	//Sumar Puntos
	@PatchMapping(path="/plusPoints/{id}", consumes="application/json")
	public TeamDTO plusTeams(@PathVariable Integer id, @RequestParam Integer points) {
		return service.plusPoints(id, points);
	}
	
	
	//--------------DELETE-----------
	@DeleteMapping(path="/deleteTeams/{id}")
	public void deleteTeam(@PathVariable(value = "id")int id) {
		service.deleteTeamById(id);
	}
	
	//--------------DELETE equipos con 0 puntos-----------
	@DeleteMapping(path="/deleteUselessTeams")
	public void deleteTeam(@RequestParam Integer points) {
		service.deleteTeamByPoints(points);
	}

	

	@GetMapping(path="/findBestVisitor")

	public TeamDTO findBestVisitor() {
		return service.findBestVisitor();
	}
	
	
	
	@GetMapping(path="/findBestLocalNative")

	public TeamDTO findBestLocalNative() {
		
		return service.findBestLocalNative();
	}
	

	@GetMapping(path="/findBestVisitorNative")

	public TeamDTO findBestVisitorNative() {
		
		return service.findBestVisitorNative();
	}

	
	@GetMapping(path="/findBestTeamLV")

	public TeamDTO findBestLocal(
			
			//1 == local , 2 == Visitor
			@RequestParam(required = true) int local) {


			if (local == 1) {
				return service.findBestLocalNative();
			}else if(local == 2) {
				return service.findBestVisitorNative();
			}else {
				return null;

			}
	}
	

//Equipo con jugador dado

	
	@GetMapping(path="/findPlayerTeam")


public TeamDTO findTeamPlayer(@RequestParam(required = true) String nom) {
	
	return service.findTeamPlayer(nom);
}
	
	
	
	//penultima query
	@PatchMapping(path="/plusPointsByPlayers")
	public void plusPointsByPlayerSet(@RequestParam (required = true) Integer points, @RequestParam (required = true) Integer players) {
		
		service.plusPointsByPlayerSet(points, players);
		
	}



}
