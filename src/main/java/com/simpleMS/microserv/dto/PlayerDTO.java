package com.simpleMS.microserv.dto;

import lombok.Data;

@Data
public class PlayerDTO {
	
	private int idPlayer;
	private String name;
	private int num;
	private String position;
//	private int idTeam;
	
	private TeamDTO team;
	
	

}
