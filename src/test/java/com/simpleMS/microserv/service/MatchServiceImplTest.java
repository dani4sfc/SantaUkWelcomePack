package com.simpleMS.microserv.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.simpleMS.microserv.dto.MatchDTO;
import com.simpleMS.microserv.entity.MatchEntity;
import com.simpleMS.microserv.mapper.MatchMapper;
import com.simpleMS.microserv.mapper.MatchMapperImpl;
import com.simpleMS.microserv.repository.MatchRepository;


public class MatchServiceImplTest {
	
	private MatchDTO dto;
	private MatchEntity entity;


	
	@Mock
	private MatchMapper mapper;
	
	@InjectMocks
	private MatchServiceImpl service;
	
	@Mock
	private MatchRepository repository;
	
	@Before
	public void beforeTeam() throws Exception{
		
		MockitoAnnotations.initMocks(this);
		
		dto = new MatchDTO();
		dto.setResultado("LOCAL");
		entity = new MatchEntity();
		dto.setIdMatch(1);
			
	}
	
	@Test
	public void findByIdTest() {
				
		//Definicion
		when(mapper.toDTO(repository.findByIdMatch(1))).thenReturn(dto);
		
		//Invocacion
		MatchDTO result = service.findById(1);
		
		//Comprobacion
		
		assertNotNull("Se esperaba un objeto Not Null", result);
		
		verify(mapper, times(1)).toDTO(repository.findByIdMatch(1));
		
	}
	
	@Test
	public void findAllTest() {
				
		//Definicion
		when(mapper.toDTO(repository.findAll())).thenReturn(new ArrayList<MatchDTO>());
		
		//Invocacion
		List<MatchDTO> result = service.findAll();
		
		//Comprobacion
		
		assertNotNull("Se esperaba un objeto Not Null", result);
		
		verify(mapper, times(1)).toDTO(repository.findAll());
		
	}
	
	@Test
	public void createTest() {
				
		//Definicion
		when(mapper.toDTO(repository.save(entity))).thenReturn(dto);
		
		//Invocacion
		MatchDTO result = service.create(dto);
		
		//Comprobacion
		
		assertNotNull("Se esperaba un objeto Not Null", result);
		
		verify(mapper, times(1)).toDTO(repository.save(entity));
		
	}
	
	@Test
	public void updateMatchTestNotFound() {
				
		//Definicion
//		when(service.findById(Mockito.eq(3))).thenReturn(null);
//		
//		//Invocacion
//		MatchDTO result = service.updateMatch(dto);
//		
//		//Comprobacion
//		
//		assertNull("Se esperaba un objeto  Null", result);
//		
//		verify(service, times(1)).findById(3);
//
//		verify(mapper, times(0)).toDTO(repository.saveAndFlush(entity));
		
	}
	
	@Test
	public void updateMatchTest() {
				
		//Definicion
		when(mapper.toDTO(repository.save(entity))).thenReturn(dto);
		
		//Invocacion
		MatchDTO result = service.create(dto);
		
		//Comprobacion
		
		assertNotNull("Se esperaba un objeto Not Null", result);
		
		verify(mapper, times(1)).toDTO(repository.save(entity));
		
	}
	


}
