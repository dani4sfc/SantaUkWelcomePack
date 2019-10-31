package com.simpleMS.microserv.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.simpleMS.microserv.dto.MatchDTO;
import com.simpleMS.microserv.entity.MatchEntity;


@Mapper(componentModel = "spring", uses=TeamMapper.class)
public interface MatchMapper {
	@Mappings({
		
		//We define here source and target,
		//The target should matches with the DTO's attribute detinition (In this case), and source should matches with the Entity's attribute definition
		//Here, we "convert" the attributes
		//We can use object.at1 to get nested attributes
		
		@Mapping(target = "idMatch", source="idMatch"),
		@Mapping(target = "local", source="local", qualifiedByName = "toTeamDTOLocal"),
		@Mapping(target = "visitante", source="visitante", qualifiedByName = "toTeamDTOLocal"),
		@Mapping(target = "resultado", source="resultado")
		
	})

	//Entity toDTO and Dto ToEntity, instances the source class and receives as parameter the target class.
	//This methods are simply converter methods, they takes an object and turn it into a different one.
	@Named("toDTOLocal")
	MatchDTO toDTOLocal(MatchEntity entity);
	
	@IterableMapping(qualifiedByName = "toDTOLocal")
	List<MatchDTO> toDTOLocal(List<MatchEntity> entity);
	

	
	@Mappings({
				
		@Mapping(target = "idMatch", source="idMatch"),
		@Mapping(target = "local", source="local", qualifiedByName = "toTeamDTONoRelation"),
		@Mapping(target = "visitante", source="visitante", qualifiedByName = "toTeamDTONoRelation"),
		@Mapping(target = "resultado", source="resultado")
		
	})

	@Named("toDTO")
	MatchDTO toDTO(MatchEntity entity);
	
	
    //List mapping, this method stave us off iterating lists in order to convert them.
	@IterableMapping(qualifiedByName = "toDTO")
	List<MatchDTO> toDTO(List<MatchEntity> entity);
	
	@Mappings({
		//target == Entity attributes, source == Dto attributes
		@Mapping(target = "idMatch", source="idMatch"),
		@Mapping(target = "local.idTeam", source="idLocal"),
		@Mapping(target = "visitante.idTeam", source="idVisitante"),
		@Mapping(target = "resultado", source="resultado")
	})
	MatchEntity toEntity(MatchDTO dto);
	

}
