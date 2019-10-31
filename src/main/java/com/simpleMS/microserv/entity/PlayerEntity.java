package com.simpleMS.microserv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDPLAYER")
	private int idPlayer;
	
	@Column(name = "NAME")
	@NotEmpty
	private String name;
	
	@Column(name = "NUM")
	private int num;
	
	@Column(name = "POSITION")
	private String position;
	
	//We comment on this attribute, since in the ManyToOne relationship we define the attribute with joinColumn.
//	@Column(name = "IDTEAM")
//	private int idTeam;
	
	
	//Relacion players-team
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="IDTEAM")
	private TeamEntity team;
	
	
}
