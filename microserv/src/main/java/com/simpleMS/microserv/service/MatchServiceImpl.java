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



//La anotación @Service es importante para que Spring identifique esta clase, así podremos hacer @Autowired al instanciarla en otra clase
@Service
public class MatchServiceImpl implements MatchService{

	@Autowired
	MatchRepository repository;
	
	@Autowired
	MatchMapper mapper;
	
	
	//En esta capa (service) hacemos la llamada a los metodos mapper para pasar de DTO a entity y viceversa
	@Override
	public MatchDTO findById(int id) {
		return mapper.toDTO(repository.findById(id).get());
	}
	
	@Override
	public List<MatchDTO> findAll() {
		
		//Con este método mapearíamos una Lista y la pasaríamos transformada a otra:
		
		//Creamos una lista de DTO y la instanciamos:
//		List<MatchDTO> result = new ArrayList<MatchDTO> ();
		
		//Cogemos una lista de la Entity y hacemos un findAll:
//		List<MatchEntity> findAll = repository.findAll();
		
		//Creamos un bucle en el que recorremos la lisya de Entitys y las vamos añadiendo a result, la lista de DTO
//		for (MatchEntity entity: findAll ) {
//			result.add(mapper.toDTO(entity));
//		}
//		return result;
		
		
		//Este método nos evita tener que hacer el procedimiento anterior con el bucle for
		return mapper.toDTO(repository.findAll());
	}
	
	@Override
	//Utilizamos el método save para crear, devolveremos un objeto tipo MatchDTO para obligar a crear ID
	public MatchDTO create(MatchDTO dto) {
		//Como el método save solo acepta entity, hacemos un mapeo a entity
		MatchEntity saved = repository.save(mapper.toEntity(dto));
		//Devolvemos el dto mapeando la entity
		return  mapper.toDTO(saved);
		//Si los atributos de DTO y Entity fueran distintos, deberíamos mapearlos uno a uno.
		
	}

	//El método update se crea igual que el create, con save, ya que si detecta que hay datos al crear, sobreescribe
	//Hay que tener en cuenta que si al updatear solo ponemos 3 atributos, habiendo 5, 2 se quedarían en null.
	//Para evitarlo, hacemos un findById, sacamos todos los atributos y los vamos seteando.
	@Override
	
	//Update: Pasamos por parametro un DTO (Json), lo actualiza y nos devuelve otro DTO
	@Transactional
	public MatchDTO updateMatch(MatchDTO input) {
		//buscamos el objeto en BBDD por ID y lo volvamos en una variable dtoBBDD
		MatchDTO dtoBBDD = this.findById(input.getIdMatch());
		
		//Creamos un nuevo DTO llamado result, en el volcaremos los datos.
		MatchDTO result = new MatchDTO();
		//Comprobamos que el DTO source, el que pasamos por parámetro, no sea null
		if (dtoBBDD != null) {
						
			
			//Creamos un Entity creado a partir del DTO pasado por parámetro
			MatchEntity entity = mapper.toEntity(dtoBBDD);	
			TeamEntity local = entity.getLocal();
			local.setIdTeam(input.getIdLocal());
			TeamEntity visitante = entity.getVisitante();
			visitante.setIdTeam(input.getIdVisitante());
	//		visitante.getMatchVisitanteSet().add(entity);
	//		local.getMatchLocalSet().add(entity);
			//Añadimos los valores a dicho Entity cogidos del DTO pasado por parámetro, salvo el ID autoincremental

			
			entity.setLocal(local);
			entity.setVisitante(visitante);
			entity.setResultado(input.getResultado());
			
			//Ya tenemos el entity con todos sus datos (Salvo el id, que se generará automaticamente al crearlo)
			//Ahora lanzamos el metodo save en el entity y este lo mapeamos en el DTO result
			MatchEntity save = repository.saveAndFlush(entity);
			result = mapper.toDTO(save);
			//Devolvemos el result actualizado
			return result;
			
			//Si no existe el DTO, devolvemos un null
		}else {
		return null;
		
		}
	
	}
	
	
	//Para el delete, solo debemos pasar un id, y se borrará el objeto con dicho id
	@Override
	public void deleteMatchById(int id) {
			
			repository.deleteById(id);			
	
	}
	
	@Override
	public 	List<MatchDTO> findByResultLocal(String name, String isLocal) {
		//TODO hacer logica para dos queries una de local y otra de visitantes segun el input  isLocal
		return mapper.toDTOLocal((repository.findByResultLocal(name)));
	}
	
	@Override
	public 	List<MatchDTO> findByResultVisitante(String name, String isVisit) {
		//TODO hacer logica para dos queries una de local y otra de visitantes segun el input  isLocal
		return mapper.toDTOLocal((repository.findByResultVisitante(name)));
	}
	
}
