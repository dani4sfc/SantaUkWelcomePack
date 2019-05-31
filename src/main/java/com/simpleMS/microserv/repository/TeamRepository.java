package com.simpleMS.microserv.repository;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simpleMS.microserv.entity.TeamEntity;

//La queries nombradas, deben ir aqui, en la cabecera

@NamedQueries({
	@NamedQuery(name = "BestLocal", query = "select t.name from TeamEntity t, MatchEntity m where t.idTeam = m.visitante.idTeam and result ='Visitante' group by t.name order  by count(t) desc")
	
}) 



@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, Integer>, JpaRepository<TeamEntity, Integer>{
	//Aqui creamos métodos personalizados, este por ejemplo, devuelve un objeto tipo TeamEntity, filtrado por el atributo teamName
	
	TeamEntity findByName(String name);
	
	List<TeamEntity> findTopByOrderByNameAsc();
	
	@Query
	TeamEntity findByPoints(int points);
		
	
	@Query("select count(t) from MatchEntity m, TeamEntity t where t.idTeam = m.visitante.idTeam and m.local.idTeam = :id")
	Integer findByResult(int id);
	
	
	//MEJOR LOCAL Y VISITOR
	
	@Query("select t from TeamEntity t, MatchEntity m where t.idTeam = m.local.idTeam and result ='Local' group by t.name order  by count(t) desc")
	//@Query("select team from TeamEntity team, MatchEntity matches where team.idTeam = matches.local.idTeam and matches.resultado = 'LOCAL' group by team.idTeam")
	TeamEntity findTopBestLocal();
	
	
	
	@Query("select t from TeamEntity t, MatchEntity m where t.idTeam = m.visitante.idTeam and result ='Visitante' group by t.name order  by count(t) desc")
	
	List<TeamEntity> findBestVisitor();
	
	
	
	
	@Query(value= "select idTeam, name,points from team where idTeam = (select filtered_final.idTeam from (select filtered.idTeam, MAX(filtered.victory) from (\r\n" + 
			"select team.idTeam, team.name, team.points, count(team.name) as victory \r\n" + 
			"from team,matches where team.idTeam = matches.idLocal \r\n" + 
			"and matches.result = 'LOCAL' \r\n" + 
			"group by team.name) filtered) filtered_final)", nativeQuery = true)
	
	
	TeamEntity findBestLocalNative();
	
	@Query(value= "select idTeam, name,points from team where idTeam = (select filtered_final.idTeam from (select filtered.idTeam, MAX(filtered.victory) from (\r\n" + 
			"select team.idTeam, team.name, team.points, count(team.name) as victory \r\n" + 
			"from team,matches where team.idTeam = matches.idVisitor \r\n" + 
			"and matches.result = 'VISITANTE' \r\n" + 
			"group by team.name) filtered) filtered_final)", nativeQuery = true)

	TeamEntity findBestVisitorNative();

	
	//Equipo que tiene al jugador dado por parámetro
	
	@Query("select t from TeamEntity t, PlayerEntity  p where t.idTeam = p.team.idTeam and p.name = :nom")
	TeamEntity findTeamPlayer(String nom);
	
//	Borrar equipos con menos de x puntos
	@Modifying
	@Transactional
	List<TeamEntity> deleteByPointsLessThan(Integer points);
	
	
	//Añadir  puntos a equipos con mas de Y jugadores
	
	@Query("update TeamEntity te set te.points = te.points + :points where te.idTeam in (select t.idTeam from TeamEntity t, PlayerEntity p where p.team.idTeam = t.idTeam group by t.idTeam  having COUNT(t.idTeam) > :players)")
	@Modifying
	@Transactional
	void plusPointsByPlayerSet(@Param("points")Integer points, @Param("players")Long players);
	
	
	
	
}
