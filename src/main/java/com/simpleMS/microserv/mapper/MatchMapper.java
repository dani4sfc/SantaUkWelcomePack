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
		
		//Aquí definimos fuente y destino, de donde cogemos el dato y a donde lo pasamos,
		//El target tiene que ser identico a la definicion del atributo de toDTO en este caso, y el source tiene que ser identico al atributo de la Entity en este caso
		//Esta es la "conversión" de atributos
		//Podemos utilizar atributo.at1 para coger atributos anidados, siendo "atributo" un objeto y at1 uno de sus atributos
		
		@Mapping(target = "idMatch", source="idMatch"),
		@Mapping(target = "local", source="local", qualifiedByName = "toTeamDTOLocal"),
		@Mapping(target = "visitante", source="visitante", qualifiedByName = "toTeamDTOLocal"),
		@Mapping(target = "resultado", source="resultado")
		
	})

	//Metodos entity to DTO y viceversa, Crean una instancia del dto y reciben como parametro un entity y viceversa.
	//Estos métodos, toDTO, toENtity, etc. que reciben una clase y devuelven otra, no son mas que un transformador, recibes un objeto MatchDto y devuelves un objetoMatchEntity
	@Named("toDTOLocal")
	MatchDTO toDTOLocal(MatchEntity entity);
	
	@IterableMapping(qualifiedByName = "toDTOLocal")
	List<MatchDTO> toDTOLocal(List<MatchEntity> entity);
	

	
	@Mappings({
		
		//Aquí definimos fuente y destino, de donde cogemos el dato y a donde lo pasamos,
		//El target tiene que ser identico a la definicion del atributo de toDTO en este caso, y el source tiene que ser identico al atributo de la Entity en este caso
		//Esta es la "conversión" de atributos
		//Podemos utilizar atributo.at1 para coger atributos anidados, siendo "atributo" un objeto y at1 uno de sus atributos
		
		@Mapping(target = "idMatch", source="idMatch"),
		@Mapping(target = "local", source="local", qualifiedByName = "toTeamDTONoRelation"),
		@Mapping(target = "visitante", source="visitante", qualifiedByName = "toTeamDTONoRelation"),
		@Mapping(target = "resultado", source="resultado")
		
	})

	//Metodos entity to DTO y viceversa, Crean una instancia del dto y reciben como parametro un entity y viceversa.
	//Estos métodos, toDTO, toENtity, etc. que reciben una clase y devuelven otra, no son mas que un transformador, recibes un objeto MatchDto y devuelves un objetoMatchEntity
	@Named("toDTO")
	MatchDTO toDTO(MatchEntity entity);
	
	
    //Mapeo de listas, este método nos sirve para evitar tener que recorrer listas de MatchDTO con bucle for para transformar
	@IterableMapping(qualifiedByName = "toDTO")
	List<MatchDTO> toDTO(List<MatchEntity> entity);
	
	@Mappings({
		//target == atributos del Entity, source == atributos del dto
		@Mapping(target = "idMatch", source="idMatch"),
		@Mapping(target = "local.idTeam", source="idLocal"),
		@Mapping(target = "visitante.idTeam", source="idVisitante"),
		@Mapping(target = "resultado", source="resultado")
	})
	MatchEntity toEntity(MatchDTO dto);
	

}
