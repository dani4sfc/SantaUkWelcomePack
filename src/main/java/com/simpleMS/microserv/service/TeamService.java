package com.simpleMS.microserv.service;

import java.util.List;

import com.simpleMS.microserv.dto.PlayerDTO;
import com.simpleMS.microserv.dto.TeamDTO;
import com.simpleMS.microserv.entity.TeamEntity;

public interface TeamService {
	
	public TeamDTO findTeamById(int id);
	
	public List<TeamDTO> findAll();
	
	public TeamDTO create (TeamDTO dto);
	
	public TeamDTO updateTeam (TeamDTO dto);
	
	public void deleteTeamById(int id);
	
	public TeamDTO findByName(String name);
	
	public TeamDTO findByPoints(int points);
	
	public int findByResult(int id);
	
	
	public TeamDTO findBestLocal();
	
	public TeamDTO findBestVisitor() ;
	
	public TeamDTO findBestLocalNative();
	
	public TeamDTO findBestVisitorNative() ;
	
	public TeamDTO findTeamPlayer(String nom);

//	public 	TeamDTO sumarPuntos(int puntos);
	
	public TeamDTO updatePoints(int id, int points);
	
	public TeamDTO plusPoints(int id, int points);
	
	public void deleteTeamById(Integer points);
	
	//Siguiente metodo, dar puntos a equipos con x jugadores
	
	public void plusPointsByPlayerSetLenghtGreaterThan(Integer players);
	
	
	//metodo complementario a anterior
	public List<TeamDTO> findByPlayerSetLenghtGreaterThan(Integer players);


}
