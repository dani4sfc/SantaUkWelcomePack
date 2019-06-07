package com.simpleMS.microserv.controller;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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
import com.simpleMS.microserv.dto.PageablePlayerDTO;
import com.simpleMS.microserv.dto.PlayerDTO;
import com.simpleMS.microserv.service.PlayerServiceImpl;
import com.simpleMS.microserv.util.PageRequestDto;

public class PlayerControllerTest {
	
	@InjectMocks
	private PlayerController controller;
	
	@Mock
	private PlayerServiceImpl service;
	
	@Before
	public void beforePlayer() throws Exception{
		
		//Iniciamos el mocks en el before, para tenerlo listo para los siguientes tests (También podríamos iniciarlo en cada test) 
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void readByIdTest() {
		
		when(service.findById(0)).thenReturn(null);
		when(service.findById(1)).thenReturn(new PlayerDTO());
		
		PlayerDTO dto = new PlayerDTO();
		dto.setName("prueba");
		dto.setNum(10);
		
		when(service.findById(5)).thenReturn(dto);
		
		PlayerDTO result = controller.readById(5);
		
		assertNotNull("El jugador es null", result);
		
		assertEquals("El Dorsal/número del jugador, no coincide", 10, result.getNum());
		
		//assertEquals("El ID no coincide", 5, result.getIdPlayer());
		
		verify(service).findById(Mockito.eq(5));
	}
	
	
	@Test
	public void readAllTest() {
		
		//definicion
		when (service.findAll()).thenReturn(new ArrayList<PlayerDTO>());
		
		//invocación
		List<PlayerDTO> result = controller.readAll();
		
		//verificacion
		assertNotNull("La lista es null", result);
		
		assertThat(result, not(result.isEmpty()));
		
		verify(service, times(1)).findAll();

	}
	
	
	@Test
	public void createTest(){
		
		PlayerDTO dto = new PlayerDTO();
		when(service.create(dto)).thenReturn(new PlayerDTO());
		
		PlayerDTO result = controller.create(dto);
		
		result.setName("Canales");
		result.setNum(10);
		
		assertNotNull("El jugador es null",result);
		assertEquals("El jugador no es 'CANALES'", "Canales", result.getName());
		
		verify(service).create(Mockito.any(PlayerDTO.class));
		
	}
	
	@Test
	public void updatePlayerTest() {
		//definir stubbing
		//PlayerDTO de entrada (input)
		PlayerDTO dto = new PlayerDTO();
		dto.setName("Canales");
		dto.setNum(10);
		//PlayerDTO de salida (return)
		PlayerDTO mockedPlayer = new PlayerDTO();
		mockedPlayer.setName("MOCKED");
		when(service.updatePlayer(dto)).thenReturn(mockedPlayer);
		//invocar metodo a probar
		PlayerDTO result = controller.updatePlayer(dto);
		//verificar resultados
		assertNotNull("El jugador es null", result);
		assertEquals("El jugador no se ha actualizado correctamente", "MOCKED", result.getName());
		
		verify(service).updatePlayer(Mockito.any(PlayerDTO.class));
		
	}
	
	//TESTS DE DELETE
	
	@Test
	public void deletePlayerTest() {

		//invocar
		controller.deletePlayer(1);
		//verificar
		verify(service, times(1)).deletePlayerById(1);
	}
	
	//TETS ADICIONALES

	@Test
	public void selectAllPlayersOrder() {
			
		//Definición
		when(service.selectAllPlayersOrder()).thenReturn(new ArrayList<PlayerDTO>());
		
		//Invocación
		List<PlayerDTO> result = controller.selectAllPlayersOrder();
		
		//Verificación
		assertNotNull("La lista de jugadores es null", result);

		verify(service, times(1)).selectAllPlayersOrder();
	}

	
	@Test
	public void selectAllPlayersOrderPage() {
		
		//argumentCaptor
		ArgumentCaptor<PageRequestDto> argumentCaptor = ArgumentCaptor.forClass(PageRequestDto.class);
		
		//Definición
		when(service.selectAllPlayersOrderPage(Mockito.any(PageRequestDto.class))).thenReturn(new PageablePlayerDTO());
		
		//Invocación
		PageablePlayerDTO result = controller.selectAllPlayersOrderPage(5, 1);
		
		//Verificación
		assertNotNull("El objeto lista es null", result);
		
		verify(service, times(1)).selectAllPlayersOrderPage(argumentCaptor.capture());
		
	}
}
