package com.simpleMS.microserv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleMS.microserv.dto.PlayerDTO;
import com.simpleMS.microserv.dto.TeamDTO;
import com.simpleMS.microserv.entity.TeamEntity;
import com.simpleMS.microserv.mapper.TeamMapper;
import com.simpleMS.microserv.repository.TeamRepository;

@Service
public class TeamServiceImpl  implements TeamService{
	
	@Autowired
	TeamRepository repository;
	
	@Autowired
	TeamMapper mapper;
	
//	@Autowired 
//	TeamEntity entity;

	public TeamDTO findTeamById(int id) {
		return mapper.toDTOTeamNoRelation(repository.findById(id).get());
		
	}
	
	public TeamDTO findByName(String name) {
		return mapper.toDTO(repository.findByName(name));
	}
	
	//prueba
	
	public TeamDTO findByPoints(int points) {
		return mapper.toDTO(repository.findByPoints(points));
	}
	
	//prueba2
	public int findByResult(int id) {
		return repository.findByResult(id);
	}

	

	public List<TeamDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}

	public TeamDTO create(TeamDTO dto) {
		TeamEntity saved= repository.save(mapper.toEntity(dto));
		return mapper.toDTO(saved);
	}

	public TeamDTO updateTeam(TeamDTO input) {
		TeamDTO dtoBBDD = this.findTeamById(input.getIdTeam());
		TeamDTO result = new TeamDTO();
		
		if(dtoBBDD != null) {
			TeamEntity entity = mapper.toEntity(dtoBBDD);
			entity.setName(input.getName());
			entity.setPoints(input.getPoints());
			result = mapper.toDTO(repository.save(entity));
		}
		return result;

		
		
	}
	
	//Establecer y sumar puntos
	
	public TeamDTO updatePoints(int id, int points) {
		TeamDTO dtoBBDD = this.findTeamById(id);
		TeamDTO result = new TeamDTO();
		
		if(dtoBBDD != null) {
			TeamEntity entity = mapper.toEntity(dtoBBDD);
			entity.setPoints(points);
			result = mapper.toDTO(repository.save(entity));
		}
		return result;
	}
	
	public TeamDTO plusPoints(int id, int points) {
		
		TeamDTO dtoBBDD = this.findTeamById(id);
		TeamDTO result = new TeamDTO();
		
		if(dtoBBDD != null) {
			TeamEntity entity = mapper.toEntity(dtoBBDD);
			entity.setPoints(entity.getPoints() + points);
			result = mapper.toDTO(repository.save(entity));
		}
		return result;
		
	}
	
	

	public void deleteTeamById(int id) {
		
		repository.deleteById(id);
		
	}
	
	//Borrar equipos con menos de puntos dados
	public void deleteTeamById(Integer points) {

		repository.deleteByPointsLessThan(points);
		
		//BRUTALIDAD:
		
//		List<TeamDTO> lista = mapper.toDTO(repository.findAll());
//		
//		TeamDTO primero = lista.get(0);
//		
//		int ultimo = lista.size();
//
//		
//		int primerId = primero.getIdTeam();
//		
//		
//		for(int i = primerId; i <= ultimo; i++) {
//			
//			
//			if (repository.findById(i).get().getPoints() <1) {
//				
//				repository.deleteById(i);
//
//			}
//			
//		}

	}

	
	//MEJOR LOCAL Y MEJOR VISITOR
	
	public TeamDTO findBestLocal() {
		//return mapper.toDTO(repository.findBestLocal());
		return mapper.toDTO(repository.findBestLocalNative());
	}
	
	public TeamDTO findBestVisitor() {
		return mapper.toDTO(repository.findBestVisitor().get(0));
	}

	public TeamDTO findBestLocalNative() {
		
		return mapper.toDTO(repository.findBestLocalNative());
	}

	public TeamDTO findBestVisitorNative() {
		
		return mapper.toDTO(repository.findBestVisitorNative());
	}
	
	//Equipo que tiene al jugador dado:
	
	public TeamDTO findTeamPlayer(String nom) {
		
		return mapper.toDTO(repository.findTeamPlayer(nom));
	}


//	public void plusPointsByPlayerSetGreaterThan(Integer points){
//		
//		List<TeamDTO> lista = mapper.toDTO(repository.findAll());
//		
//		TeamDTO primero = lista.get(0);
//		
//		int ultimo = lista.size();
//
//		
//		int primerId = primero.getIdTeam();
//		
//		
//		for(int i = primerId; i <= ultimo; i++) {
//			
//			
//			if (repository.findById(i).get().getPlayerSet().size() <1) {
//				
//				repository.findById(i).get().setPoints(repository.findById(i).get().getPoints() + points);
//
//			}
//			
//		}
//	
//	}

}
