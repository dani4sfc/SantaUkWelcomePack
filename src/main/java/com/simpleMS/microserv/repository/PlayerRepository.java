package com.simpleMS.microserv.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.simpleMS.microserv.entity.PlayerEntity;


@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, Integer>, JpaRepository<PlayerEntity, Integer>, PagingAndSortingRepository<PlayerEntity,Integer> {

	
	@Query("select p from PlayerEntity p order by p.name")
	public List<PlayerEntity> selectAllPlayersOrder();
	
	//We can also use NativeSQL using the parameter NativeQuery, if we need to use a SQL query
//	@Query(value="select p from PlayerEntity p order by p.name 	limit :max offset :off", nativeQuery=true)
	
	public Page<PlayerEntity> findAll(Pageable pageable);
		
	
	

	
	

	
}
