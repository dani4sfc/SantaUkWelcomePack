package com.simpleMS.microserv.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Con la anoptación Entity, informamos a Spring que esta es la clase que contiene el modelo de datos de la bbdd.
@Entity
//La anotacion table sirve para dar informacion sobre la tabla, como el nombre que tiene en la bbdd
@Table(name ="matches")
//Las anotaciones @Getter, @Setter, @NoArgsConstructor y @AllArgsContructor, son de lombok, que permite crear dinamicamente dichos métodos.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {
	//Con la anotación @OneToMany establecemos la relacion entre tablas, en este caso, de una a muchas.
	//La anotación @Id indica que el siguiente atributo es el id de la tabla
	@Id
	//La anotacion @GeneratedValue indica el modo en el que se genera el id, en este caso, autoincremental
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//Con la anotacion @Column, al igual que con @Table, damos info de la columna, como el nombre que lleva en la bbdd
	@Column(name ="IDMATCH")
//	@NotEmpty
	private int idMatch;
	
	//Al ser una relacion N:1, aqui establecemos el @ManyToOne y hacemos un Join con la clave.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDLOCAL")
	private TeamEntity local;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDVISITOR")
	private TeamEntity visitante;
	
	@Column(name ="RESULT")
	@NotEmpty
	private String resultado;
	
	
	
	
	//LOS ATRIBUTOS SIEMPRE SON PRIVATE, YA QUE LOS COGEMOS CON LOS GETTERS
	

}
