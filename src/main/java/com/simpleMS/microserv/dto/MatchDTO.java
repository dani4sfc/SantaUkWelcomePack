package com.simpleMS.microserv.dto;

import lombok.Data;

//El DTO es como un transformador entre Entity y la API, al igual que Entity, lo es entre BBDD y DTO o Services
//Dichas transformaciones se realizan mediante mapeos bidireccionales

//La anotacion @Data es de lombok, agrupa todas las anotaciones de getters, setters y constructores.
@Data
public class MatchDTO {
	

	private int idMatch;
	private int idLocal;
	private int idVisitante;
	private String resultado;
	
	private TeamDTO local;
	
	private TeamDTO visitante;
	
}
