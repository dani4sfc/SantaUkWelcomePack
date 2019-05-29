package com.simpleMS.microserv.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.simpleMS.microserv.dto.PlayerDTO;
import com.simpleMS.microserv.entity.PlayerEntity;

@Mapper(componentModel = "spring", uses=TeamMapper.class)
public interface PlayerMapper {
	
	@Mappings({
		
		@Mapping(target="idPlayer",source="idPlayer"),
		@Mapping(target="name",source="name"),
		@Mapping(target="num",source="num"),
		@Mapping(target="position",source="position"),
		@Mapping(target="team",source="team", qualifiedByName="toTeamDTONoRelation")
	})
	PlayerDTO toDTO(PlayerEntity entity);
	
	@Named("toDTONoRelation")
	@Mappings({
		@Mapping(target="idPlayer",source="idPlayer"),
		@Mapping(target="name",source="name"),
		@Mapping(target="num",source="num"),
		@Mapping(target="position",source="position"),
		@Mapping(target="team", source="team", ignore = true)
	})
	PlayerDTO toDTONoRelation(PlayerEntity entity);
	
	
	@IterableMapping(qualifiedByName = "toDTONoRelationPlayer")

	List<PlayerDTO> toDTO(List<PlayerEntity> entity);

	
	@Mappings({
		
		@Mapping(target="idPlayer",source="idPlayer"),
		@Mapping(target="name",source="name"),
		@Mapping(target="num",source="num"),
		@Mapping(target="position",source="position"),
		@Mapping(target="team",source="team")
	})
	
	PlayerEntity toEntity(PlayerDTO dto);
	
	List<PlayerEntity> toEntity(List<PlayerDTO> dto);
	
	@Named("toDTONoRelationPlayer")
	@Mappings({
		@Mapping(target="idPlayer",source="idPlayer"),
		@Mapping(target="name",source="name"),
		@Mapping(target="num",source="num"),
		@Mapping(target="position",source="position"),
		@Mapping(target="team", source="team", qualifiedByName="toTeamNoRelationPlayer")
	})
	PlayerDTO toDTONoRelationPlayer(PlayerEntity entity);
	



}
