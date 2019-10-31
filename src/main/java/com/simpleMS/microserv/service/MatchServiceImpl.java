package com.simpleMS.microserv.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.simpleMS.microserv.dto.MatchDTO;
import com.simpleMS.microserv.entity.MatchEntity;
import com.simpleMS.microserv.entity.TeamEntity;
import com.simpleMS.microserv.mapper.MatchMapper;
import com.simpleMS.microserv.repository.MatchRepository;



//The @Service annotation it's necessary so that Spring detects this class as Service class, So that we will be able to use @Autowired while instantiate it on another class
@Service
public class MatchServiceImpl implements MatchService{

	@Autowired
	MatchRepository repository;
	
	@Autowired
	MatchMapper mapper;
	
	
	//On this layer (service) we call the mapper methods to convert Entities and Dtos
	@Override
	public MatchDTO findById(int id) {
		return mapper.toDTO(repository.findById(id).get());
	}
	
	@Override
	public List<MatchDTO> findAll() {
		
		//With this method we would map a List and pass it transformed to another one:
		
		//We create a DTO list y instanciate it:
//		List<MatchDTO> result = new ArrayList<MatchDTO> ();
		
		//We take an Entity list and use findAll:
//		List<MatchEntity> findAll = repository.findAll();
		
		//We iterate the Entity list and add each iteration to the result list (DTO)
//		for (MatchEntity entity: findAll ) {
//			result.add(mapper.toDTO(entity));
//		}
//		return result;
		
		
		//This method avoids us having to do the previous procedure with the for loop.
		return mapper.toDTO(repository.findAll());
	}
	
	@Override
	//We use the save method to create, we will return a MatchDTO object to force to create ID.
	public MatchDTO create(MatchDTO dto) {
		//The ave method just accepts entity, so we map to Entity
		MatchEntity saved = repository.save(mapper.toEntity(dto));
		//we return the DTO mapping entity
		return  mapper.toDTO(saved);
		//If the attributes of Entity and DTO were distinct, we should map them individually
		
	}

	//The update method is created in the same way as the create method, with save, because if it detects that there is data when creating, it overwrites
	//It must be taken into account that if when updating we only put 3 attributes, having 5, 2 would remain in null.
	//To avoid it, we make a findById, we take out all the attributes and we set them.
	@Override
	
	//Update: We pass a DTO as parameter (Json), and we receive the updated one
	@Transactional
	public MatchDTO updateMatch(MatchDTO input) {
		
		//We look for the object in BBDD by ID and dumpt it in a variable dtoBBDD
		MatchDTO dtoBBDD = this.findById(input.getIdMatch());
		
		//We create a new DTO: result, we will dump the data into result.
		MatchDTO result = new MatchDTO();
		//We check that the DTO parameter is not null (In order to avoid nullPointerExceptions)
		if (dtoBBDD != null) {
						
			
			//We create an Entity created from the DTO passed by parameter
			MatchEntity entity = mapper.toEntity(dtoBBDD);	
			TeamEntity local = entity.getLocal();
			local.setIdTeam(input.getIdLocal());
			TeamEntity visitante = entity.getVisitante();
			visitante.setIdTeam(input.getIdVisitante());
	//		visitante.getMatchVisitanteSet().add(entity);
	//		local.getMatchLocalSet().add(entity);
			//We add the values to that Entity taken from the DTO passed by parameter, except the autoincremental ID

			
			entity.setLocal(local);
			entity.setVisitante(visitante);
			entity.setResultado(input.getResultado());
			
			//We already have the entity with all its data (Except the id, which will be generated automatically when creating it)
			//We launch the save method on entity and we map it on the result DTO
			MatchEntity save = repository.saveAndFlush(entity);
			result = mapper.toDTO(save);
			//We return the updated result
			return result;
			
			//If doesn't exist, retuns null
		}else {
		return null;
		
		}
	
	}
	
	
	//For deleting, we just give it an id by parameter, and the object with that id will be deleted.
	@Override
	public void deleteMatchById(int id) {
			
			repository.deleteById(id);			
	
	}
	
	@Override
	public 	List<MatchDTO> findByResultLocal(String name, String isLocal) {
		return mapper.toDTOLocal((repository.findByResultLocal(name)));
	}
	
	@Override
	public 	List<MatchDTO> findByResultVisitante(String name, String isVisit) {
		return mapper.toDTOLocal((repository.findByResultVisitante(name)));
	}
	
}
