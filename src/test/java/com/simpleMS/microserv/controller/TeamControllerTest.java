package com.simpleMS.microserv.controller;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.simpleMS.microserv.dto.TeamDTO;
import com.simpleMS.microserv.service.TeamServiceImpl;

public class TeamControllerTest {
	
	private TeamDTO dto;

	@InjectMocks
	private TeamController controller;
	
	@Mock
	private TeamServiceImpl service;
	
	@Before
	public void beforeTeam() throws Exception{
		
		MockitoAnnotations.initMocks(this);
		
		dto = new TeamDTO();
		dto.setName("Sevilla");
		
		
	}
	
	@Test
	public void readByIdTest() {
		
		when(service.findTeamById(0)).thenReturn(null);
		when(service.findTeamById(1)).thenReturn(new TeamDTO());
		
		dto = new TeamDTO();
		dto.setName("MOCK");
		dto.setPoints(10);

		
		when(service.findTeamById(3)).thenReturn(dto);
		
		TeamDTO result = controller.readById(3);
		
		assertNotNull("El equipo es null", dto);
		assertEquals("El equipo no tiene los puntos correctos",10, result.getPoints());
		
		verify(service).findTeamById(Mockito.eq(3));
	}
	
	@Test
	public void readAllTest() {
		
		when(service.findAll()).thenReturn(new ArrayList<TeamDTO>());
		
		List<TeamDTO> result = controller.readAll();
		
		assertNotNull("El equipo es null", result);
		assertThat("La lista no coincide",result.size(), is(new ArrayList<TeamDTO>().size()));
		verify(service, times(1)).findAll();
		
	}
	
	@Test
	public void createTest() {
		
		when(service.create(dto)).thenReturn(new TeamDTO());
		
		TeamDTO result = controller.create(dto);
		
		result.setName("MOCK");
		result.setPoints(5);
		
		assertNotNull("El equipo es null", result);
		assertEquals("El nombre no coincide", "MOCK", result.getName());
		
		verify(service).create(Mockito.any(result.getClass()));
		
	}
	
	@Test
	public void updateTeamsTest() {
		
		TeamDTO mockedTeam = new TeamDTO();
		mockedTeam.setName("MOCK");
		
		//Al igual que el update original, recibimos un objeto y devolvemos otro (Valores distintos).
		when(service.updateTeam(dto)).thenReturn(mockedTeam);
		
		//Como aqui le estamos pasando por parametro dto, el controller llamará al service (mock) devolviendo dto, y arriba hemos definido que si se llama al mock con dto como parámetro, devuelva el otro que hemos construido.
		TeamDTO result = controller.updateTeams(dto);
		
		//ArgumentCaptor: para comprobar los parámetros de un objeto en un momento intermedio del test
		ArgumentCaptor<TeamDTO> argumentCapt = ArgumentCaptor.forClass(TeamDTO.class);
		
		assertNotNull("El equipo es null", result);
		assertNotEquals(dto, result);
		
		verify(service).updateTeam(argumentCapt.capture());
		
		TeamDTO captured = argumentCapt.getValue();
		
		//Lo que nos permite el argument captor es detectar el valor de una variable al ejecutar el service mockeado
		assertEquals(captured.getName(), "Sevilla");
	}
	
	@Test
	public void deleteTeamTest() {
		//invocar
		controller.deleteTeam(1);
		//verificar
		verify(service, times(1)).deleteTeamById(1);
	}
	
	//TESTS METODOS ADICIONALES
	
	@Test
	public void updatePointsTest() {
		
		//Definicion
		when(service.updatePoints(2, 10)).thenReturn(dto);
		
		//Invocacion
		TeamDTO result = controller.updateTeams(2, 10);
		
		//Comprobacion
		assertNotNull("El equipo es null", result);
		verify(service, times(1)).updatePoints(2, 10);
	}
	
	@Test
	public void plusPointsTest() {
		
		//Definicion
		when(service.plusPoints(2, 10)).thenReturn(dto);
		
		//Invocacion
		TeamDTO result = controller.plusTeams(2, 10);
		
		//Comprobacion
		assertNotNull("El equipo es null", result);
		verify(service, times(1)).plusPoints(2, 10);
	}
	
	
	@Test
	public void deleteTeamPointsTest() {
		
		//Invocacion
		controller.deleteTeamPoints(0);
		
		//Comprobacion

		verify(service, times(1)).deleteTeamByPoints(0);
		
	}
	
	@Test
	public void findBestVisitorTest() {
		
		//Definicion
		when(service.findBestVisitor()).thenReturn(dto);
		
		//Invocacion
		TeamDTO result = controller.findBestVisitor();
		
		//Comprobacion

		assertNotNull(result);
				
		verify(service, times(1)).findBestVisitor();
		
	}
	
	@Test
	public void findBestLocalNativeTest() {
		
		//Definicion
		when(service.findBestLocalNative()).thenReturn(dto);
		
		//Invocacion
		TeamDTO result = controller.findBestLocalNative();
		
		//Comprobacion

		assertNotNull(result);
				
		verify(service, times(1)).findBestLocalNative();
		
	}
	
	
	@Test
	public void findBestVisitorNativeTest() {
		
		//Definicion
		when(service.findBestVisitorNative()).thenReturn(dto);
		
		//Invocacion
		TeamDTO result = controller.findBestVisitorNative();
		
		//Comprobacion

		assertNotNull(result);
				
		verify(service, times(1)).findBestVisitorNative();
		
	}
	
	
	@Test
	public void findBestLocalVisitorTest_notFound() {
		
		//Invocacion
		//Cuando no se ejecuta una llamada  auna función, como en el caso de un else que devuelve un null, no utilizamos when(mock)
		TeamDTO result = controller.findBestLocalVisitor(3);
		
		
		//Comprobacion

		//assertNull("No es null", result);
		assertNull("Se esperada nulo",result);		
		verify(service, times(0)).findBestVisitorNative();
		verify(service, times(0)).findBestLocalNative();
		
	}
		
	@Test
	public void findBestLocalVisitorTest_bestLocal() {
		
		//Definicion
		when(service.findBestLocalNative()).thenReturn(dto);
				
		//Invocacion
		TeamDTO result = controller.findBestLocalVisitor(1);
		
		//Comprobacion
		assertNotNull(result);
				
		verify(service, times(1)).findBestLocalNative();
		
	}
	
	@Test
	public void findBestLocalVisitorTest_bestVisitor() {
		
		//Definicion
		when(service.findBestVisitorNative()).thenReturn(dto);
				
		//Invocacion
		TeamDTO result = controller.findBestLocalVisitor(2);
		
		//Comprobacion

		assertNotNull(result);
				
		verify(service, times(1)).findBestVisitorNative();
		
	}
	
	@Test
	public void findTeamPlayerTest() {
		
		//Definicion
		TeamDTO dto2 = new TeamDTO();
		dto2.setName("MOCK");
		when(service.findTeamPlayer("MOCK")).thenReturn(dto2);
				

		//Invocacion
		TeamDTO result = controller.findTeamPlayer("MOCK");
		
		//Comprobacion

		assertNotNull("Se esperaba NOT NULL",result);
		
		assertEquals(dto2.getName(), "MOCK");
						
		verify(service, times(1)).findTeamPlayer(dto2.getName());
		
	}
	
	@Test
	public void plusPointsByPlayerSet() {
				
		//Invocacion
		controller.plusPointsByPlayerSet(2, 5);
		
		//Comprobacion
					
		verify(service, times(1)).plusPointsByPlayerSet(2, 5);
		
	}
	
	

}
