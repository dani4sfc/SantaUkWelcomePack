package com.simpleMS.microserv.service;

import java.util.List;

import com.simpleMS.microserv.dto.MatchDTO;
import com.simpleMS.microserv.dto.PageableMatchDTO;
import com.simpleMS.microserv.util.PageRequestDto;

//Aqui definiremos los métodos que después implementaremos en ServiceImpl, allí, le pasaremos el contenido del repository y les pasaremos el mapeo.
//Solo creamos los métodos, sin configurarlos, para abstraerlos y poder implementarlos y variarlos cada vez que necesitemos

public interface MatchService {
	
	//Métodos CRUD: create, find, delete y update. Los definimos aquí y los implementamos en Serviceimpl
	
	//--------------READ:
	
	//Buscar un DTO
	public MatchDTO findById(int i);
	
	//Buscar todos los DTO
	public List<MatchDTO> findAll();
	
	
	
	//-------------CREATE:
	
	//Normalmente un create sería tipo void, pero en este caso, debe ser tipo DTO para que se le asigne la ID automaticamente, sinó tendriamos un DTO sin ID y al hacer otras operaciones con el, se le crearían varias IDs, creando duplicidad.
	//public void create(MatchDTO dto);
	
	//Este sería en create correcto, devolviendo un tipo dto, para que se le asigne un id.
	public MatchDTO create(MatchDTO dto);
	
	//-------------UPDATE:
	
	//Aqui devolvemos un MatchDTO, para evitar duplicidad al asignar diferentes ids al mismo objeto, pasamos por parametro otro DTO
	//Una vez pasemos el DTO por parametro, comprobamos que exista (Buscando por su id) y pasamos sus datos al nuevo
	public MatchDTO updateMatch(MatchDTO input);
	
	//--------------DELETE:
	
	//El delete debe ser void, ya que no devuelve ningún parametro.
	public void deleteMatchById(int id);
	
	/**
	 * Hola
	 * @param name
	 * @param isLocal
	 * @return
	 */
	public 	List<MatchDTO> findByResultLocal(String name, String isLocal);

	public List<MatchDTO> findByResultVisitante(String name, String isVisit);
	
	public PageableMatchDTO findMatchByResult(String result, PageRequestDto pageReq);

}
