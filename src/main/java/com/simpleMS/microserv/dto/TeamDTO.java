package com.simpleMS.microserv.dto;

import java.util.HashSet;
import java.util.Set;


import lombok.Data;

@Data
public class TeamDTO {
	

	private int idTeam;

	private String name;
	
	private int points;
	

	private Set<PlayerDTO> playerSet = new HashSet<PlayerDTO>();
	

	
}
