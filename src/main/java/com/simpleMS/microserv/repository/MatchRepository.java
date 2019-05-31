package com.simpleMS.microserv.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simpleMS.microserv.entity.MatchEntity;

//Los repositorios siempre deben llevar la anotación @Repository encima de la clase, para que spring los identifique
@Repository
//Aquí extendemos de otras interfaces que ofrecen métodos básicos

//Aqui pasamos como parametro las entity, para trabajar con ellas
public interface MatchRepository extends CrudRepository<MatchEntity, Integer>, JpaRepository<MatchEntity, Integer>, PagingAndSortingRepository<MatchEntity,Integer> {

	//Aquí definiríamos métodos específicos que necesitemos.
	MatchEntity findByIdMatch(int idMatch);

//	public Page<MatchEntity> findAll(Pageable pageable);

	
	@Query("select m from MatchEntity m where m.local.name = :name")
	List<MatchEntity> findByResultLocal(@Param("name") String name);

	
	
	@Query("select m from MatchEntity m where m.visitante.name = :name")
	List<MatchEntity> findByResultVisitante(@Param("name") String name);
	
	
	//ULTIMA QUERY
	public Page<MatchEntity> findByResultado(@Param("result") String result, Pageable pageable);

}
