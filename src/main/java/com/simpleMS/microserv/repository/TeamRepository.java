package com.simpleMS.microserv.repository;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simpleMS.microserv.entity.TeamEntity;

//Named queries must be defined here, on the "header"

@NamedQueries({
	@NamedQuery(name = "BestLocal", query = "select t.name from TeamEntity t, MatchEntity m where t.idTeam = m.visitante.idTeam and result ='Visitante' group by t.name order  by count(t) desc")
	
}) 



@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, Integer>, JpaRepository<TeamEntity, Integer>{
	//Custom queries, this for example, returns a teamEntity object, filtering the teamName attribute 
	
	TeamEntity findByName(String name);
	
	List<TeamEntity> findTopByOrderByNameAsc();
	
	@Query
	TeamEntity findByPoints(int points);
		
	
	@Query("select count(t) from MatchEntity m, TeamEntity t where t.idTeam = m.visitante.idTeam and m.local.idTeam = :id")
	Integer findByResult(int id);
	

	
	//(@Param ('id')int id)
	
	//Best local/visitor
	
	@Query("select t from TeamEntity t, MatchEntity m where t.idTeam = m.local.idTeam and result ='Local' group by t.name order  by count(t) desc")
	//@Query("select team from TeamEntity team, MatchEntity matches where team.idTeam = matches.local.idTeam and matches.resultado = 'LOCAL' group by team.idTeam")
	TeamEntity findTopBestLocal();
	
	//List<TeamEntity> resultList= BestLocal.setMaxResults(100).getResultList();
	
	
	@Query("select t from TeamEntity t, MatchEntity m where t.idTeam = m.visitante.idTeam and result ='Visitante' group by t.name order  by count(t) desc")
	
	List<TeamEntity> findBestVisitor();
	
	
	
	
	@Query(value= "select idTeam, name,points from team where idTeam = (select filtered_final.idTeam from (select filtered.idTeam, MAX(filtered.victory) from (\r\n" + 
			"select team.idTeam, team.name, team.points, count(team.name) as victory \r\n" + 
			"from team,matches where team.idTeam = matches.idLocal \r\n" + 
			"and matches.result = 'LOCAL' \r\n" + 
			"group by team.name) filtered) filtered_final)", nativeQuery = true)
	
	//@Query("select team.idTeam, team.name,team.points from TeamEntity team, MatchEntity matches where team.idTeam = matches.idLocal and matches.result = 'LOCAL' group by team.idTeam")
	
	TeamEntity findBestLocalNative();
	
	@Query(value= "select idTeam, name,points from team where idTeam = (select filtered_final.idTeam from (select filtered.idTeam, MAX(filtered.victory) from (\r\n" + 
			"select team.idTeam, team.name, team.points, count(team.name) as victory \r\n" + 
			"from team,matches where team.idTeam = matches.idVisitor \r\n" + 
			"and matches.result = 'VISITANTE' \r\n" + 
			"group by team.name) filtered) filtered_final)", nativeQuery = true)

	TeamEntity findBestVisitorNative();

	
//	@NamedQueries(value = {
//	@NamedQuery(name = "BestLocal", query = "select t.name from TeamEntity t, MatchEntity m where t.idTeam = m.visitante.idTeam and result ='Visitante' group by t.name order  by count(t) desc"))
//	
//	TeamEntity findBestVisitor();
//	
//	BestLocal.setMax()
	
	//Team which contains a player with the given name (By parameter)
	
	@Query("select t from TeamEntity t, PlayerEntity  p where t.idTeam = p.team.idTeam and p.name = :nom")
	TeamEntity findTeamPlayer(String nom);
	
//	Delete teams by points
	@Modifying
	@Transactional
	List<TeamEntity> deleteByPointsLessThan(Integer points);
	

	@Modifying
	@Transactional
	void plusPointsByPlayerSetGreaterThan(Integer points);
	
	
	//select *, count(t.idTeam) as contador from team t, player p where p.idTeam = t.idTeam group by t.idTeam  having COUNT(contador) > 1;
//	@Query("select t, from TeamEntity t, PlayerEntity p where p.idTeam = t.idTeam group by t.idTeam having count (t.idTeam) > 1")
//	List<TeamEntity> selectLargo();
	
	
}
