package com.simpleMS.microserv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simpleMS.microserv.entity.MatchEntity;

//In order to let Spring detect our repositories, we need to define them with the @Repository annotation
@Repository
//We will extends of other interfaces which provides us basics CRUD methods

//We pass entity+id as parameter
public interface MatchRepository extends CrudRepository<MatchEntity, Integer>, JpaRepository<MatchEntity, Integer>{

	//We have already "imported" the basic methods of the extended interfaces, but here, we can define 
	//Additional and custom queries
	MatchEntity findByIdMatch(int idMatch);

	
	//We can use the @Query (JPA) annotation, and associate it to a method
	@Query("select m from MatchEntity m where m.local.name = :name")
	List<MatchEntity> findByResultLocal(@Param("name") String name);

	
	@Query("select m from MatchEntity m where m.visitante.name = :name")
	List<MatchEntity> findByResultVisitante(@Param("name") String name);
}
