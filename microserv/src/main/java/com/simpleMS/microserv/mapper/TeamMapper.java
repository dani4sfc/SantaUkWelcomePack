package com.simpleMS.microserv.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.simpleMS.microserv.dto.TeamDTO;
import com.simpleMS.microserv.entity.TeamEntity;

@Mapper(componentModel = "spring", uses=PlayerMapper.class)
public interface TeamMapper {
	
	@Mappings({
		
		@Mapping(target="idTeam", source="idTeam"),
		@Mapping(target="name", source="name"),
		@Mapping(target="points", source="points"),
		@Mapping(target="playerSet", source="playerSet")
	})
	@Named("teamMapperToDTO")
	TeamDTO toDTO(TeamEntity entity);
	

	@Mappings({
		
		@Mapping(target="idTeam", source="idTeam"),
		@Mapping(target="name", source="name"),
		@Mapping(target="points", source="points"),
		@Mapping(target="playerSet", source="playerSet", ignore=true)
		
	})
	
	@Named ("toTeamDTOLocal")
	TeamDTO toDTONoRelation(TeamEntity entity);
	
	@IterableMapping(qualifiedByName = "toTeamDTONoRelation")
	List<TeamDTO> toDTO(List<TeamEntity> entity);
	
	@Named ("toTeamDTONoRelation")
	@Mappings({
		@Mapping(target="idTeam", source="idTeam"),
		@Mapping(target="name", source="name"),
		@Mapping(target="points", source="points"),
		@Mapping(target="playerSet", source="playerSet", qualifiedByName  = "toDTONoRelation")
	})
	TeamDTO toDTOTeamNoRelation(TeamEntity entity);
	


	@Mappings({
		
		@Mapping(target="idTeam", source="idTeam"),
		@Mapping(target="name", source="name"),
		@Mapping(target="points", source="points")
		
	})
	
	TeamEntity toEntity(TeamDTO dto);
	
	@Named ("toTeamNoRelationPlayer")
	@Mappings({
		//@Mapping(target="idTeam", source="idTeam"),
		@Mapping(target="name", source="name"),
		@Mapping(target="points", source="points"),
		@Mapping(target="playerSet", source="playerSet", ignore=true)
	})
	TeamDTO toTeamNoRelationPlayer(TeamEntity entity);



}
