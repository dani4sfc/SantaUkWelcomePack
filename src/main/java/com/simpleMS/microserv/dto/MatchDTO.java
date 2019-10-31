package com.simpleMS.microserv.dto;

import lombok.Data;

//The DTO is like a transformer between Entity and the API, just like Entity, it is a transformer between BBDD and DTO or Services.
//These transformations are carried out by means of bidirectional mappings.

//The annotation @Data is from lombok, it groups all the annotations of getters, setters and builders.
@Data
public class MatchDTO {
	

	private int idMatch;
	private int idLocal;
	private int idVisitante;
	private String resultado;
	
	private TeamDTO local;
	
	private TeamDTO visitante;
	
}
