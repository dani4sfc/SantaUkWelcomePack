package com.simpleMS.microserv.service;

import java.util.List;

import com.simpleMS.microserv.dto.TeamDTO;

public interface TeamService {
	
	public TeamDTO findTeamById(int id);
	
	public List<TeamDTO> findAll();
	
	public TeamDTO create (TeamDTO dto);
	
	public TeamDTO updateTeam (TeamDTO dto);
	
	public void deleteTeamById(int id);
	
	public TeamDTO findByName(String name);
	
	public TeamDTO findByPoints(int points);
	
	public int findByResult(int id);
	
		
	public TeamDTO findBestVisitor() ;
	
	public TeamDTO findBestLocalNative();
	
	public TeamDTO findBestVisitorNative() ;
	
	public TeamDTO findTeamPlayer(String nom);
	
	public TeamDTO updatePoints(int id, int points);
	
	public TeamDTO plusPoints(int id, int points);
	
	
	public void deleteTeamByPoints(Integer points);

	
	//Siguiente metodo, dar puntos a equipos con x jugadores
	
	void plusPointsByPlayerSet(Integer points, Integer players);
	
//	public List<TeamDTO> plusPointsByPlayerSet2(int points, List<TeamEntity> listOfPlayers); 
	

}
