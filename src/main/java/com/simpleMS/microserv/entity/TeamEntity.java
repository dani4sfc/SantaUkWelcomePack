package com.simpleMS.microserv.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Table(name = "team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTEAM")
	private int idTeam;
	
	//Declaramos una relacion de muchos a 1, que estará relacionada con MatchEntity (1:N)
	@OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
//    @JoinColumn(name="IDMATCH", nullable=false)	
	//Instanciamos el MatchEntity que usaremos para mapear la relacion (Definido en el MatchEntity)
	private Set<MatchEntity> matchLocalSet = new HashSet<MatchEntity>();
	
	@OneToMany(mappedBy = "visitante", cascade = CascadeType.ALL)
//  @JoinColumn(name="IDMATCH", nullable=false)	
	//Instanciamos el MatchEntity que usaremos para mapear la relacion (Definido en el MatchEntity)
	private Set<MatchEntity> matchVisitanteSet = new HashSet<MatchEntity>();
	

//	@NotEmpty
	@Column(name = "NAME", unique = true)
	private String name;
	@Column(name = "POINTS")
	private int points;
	
	//Relacion team-players
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Set<PlayerEntity> playerSet = new HashSet<PlayerEntity>();
}
