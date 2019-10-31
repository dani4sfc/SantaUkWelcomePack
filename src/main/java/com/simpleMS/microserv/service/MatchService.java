package com.simpleMS.microserv.service;

import java.util.List;

import com.simpleMS.microserv.dto.MatchDTO;

//We define here the methods that we will implement on ServiceImpl classes, on that class, we will use the repository and the mapper.
//We define here the methods, without adding logic, in order to abstract them, so we can call them when we need

public interface MatchService {
	
	//CRUD methods: create, find, delete and update. we define them here and implements them on Serviceimpl
	
	//--------------READ:
	
	//Search a DTO
	public MatchDTO findById(int i);
	
	//Search all DTO
	public List<MatchDTO> findAll();
	
	
	
	//-------------CREATE:
	
	//Normally a create would be void type, but in this case, it must be DTO type so that the ID is assigned automatically, 
	//otherwise we would have a DTO without ID and when doing other operations with it, several IDs would be created, creating duplicity.	
	
	//public void create(MatchDTO dto);
	
	//This would be a correct create, returning a dto type, so that an id is assigned to it.
	public MatchDTO create(MatchDTO dto);
	
	//-------------UPDATE:
	
	//Here we return a MatchDTO, to avoid duplicity when assigning different ids to the same object, we pass by parameter another DTO
	//Once we pass the DTO by parameter, we check that it exists (Searching by its id) and we pass its data to the new one.
	public MatchDTO updateMatch(MatchDTO input);
	
	//--------------DELETE:
	
	//Delete method must be void, because it's not going to return anything.
	public void deleteMatchById(int id);
	
	/**
	 * Hola
	 * @param name
	 * @param isLocal
	 * @return
	 */
	public 	List<MatchDTO> findByResultLocal(String name, String isLocal);

	public List<MatchDTO> findByResultVisitante(String name, String isVisit);

}
