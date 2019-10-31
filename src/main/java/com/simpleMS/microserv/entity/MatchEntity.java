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

//With Entity anoptation, we inform Spring that this is the class that contains the bbdd data model.
@Entity
//La anotacion table sirve para dar informacion sobre la tabla, como el nombre que tiene en la bbdd
@Table(name ="matches")
//The table annotation is used to give information about the table, such as the name it has in the bbdd.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {
	//With the annotation @OneToMany we establish the relationship between tables, in this case, from one to many.
	//The @Id annotation indicates that the following attribute is the table id
	@Id
	//The annotation @GeneratedValue indicates how the id is generated, in this case, autoincremental.
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//With the annotation @Column, as with @Table, we give info of the column, as the name that takes in the bbdd
	@Column(name ="IDMATCH")
//	@NotEmpty
	private int idMatch;
	
	//Al to be a relation N:1, here we establish the @ManyToOne and make a Join with the key.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDLOCAL")
	private TeamEntity local;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDVISITOR")
	private TeamEntity visitante;
	
	@Column(name ="RESULT")
	@NotEmpty
	private String resultado;
	
	
	
	
	// THE ATTRIBUTES ARE ALWAYS PRIVATE, SINCE WE TAKE THEM WITH THE GETTERS
	

}
