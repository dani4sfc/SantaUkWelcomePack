package com.simpleMS.microserv.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.simpleMS.microserv.dto.MatchDTO;
import com.simpleMS.microserv.dto.PageableMatchDTO;
import com.simpleMS.microserv.service.MatchService;
import com.simpleMS.microserv.util.PageRequestDto;

public class MatchControllerTest {

	//Indicamos donde vamos a inyectar los mocks
	@InjectMocks
	private MatchController controller;

	//Definimos un mock, para inyectarlo posteriormente
	@Mock
	private MatchService service;

	//La anotacion se ejecuta antes que los tests, aquí por ejemplo podemos inyectar los mocks, que se utilizarán en dichos tests
	@Before
	public void beforeMatch() throws Exception {
		
		//Iniciamos los mocks que haya inyectados con la anotaciones @Mock
		MockitoAnnotations.initMocks(this);
		
		//Definimos los posibles resultados que nos puede devolver el test, en este caso 2, o encuentra algo, o no.
		//En un caso devolvemos un null y en otro devolvemos un nuevo objeto.
		
		//Clausulas when genéricas aquí, las específicas en el propio TEST
		when(service.findById(0)).thenReturn(null);

		when(service.findById(1)).thenReturn(new MatchDTO());
		

	}

	//Con @Test definimos los tests unitarios
	@Test
	public void readByIdTest() {
		// 1: Definir el comportamiento de las dependencias externas (stubbing)
		when(service.findById(0)).thenReturn(null);

		when(service.findById(1)).thenReturn(new MatchDTO());
		
		//Creamos una instancia de MatchDTO con unos valores concretos
		MatchDTO dto = new MatchDTO();
		dto.setResultado("MOCK");
		dto.setIdLocal(2);
		//Indicamos que cuando se busque un MatchDTO con id 10, que se devuelva el MatchDTO que creamos previamente.
		when(service.findById(10)).thenReturn(dto);
		// 2: Invocar al metodo de la clase que queremos probar
		MatchDTO result = controller.readById(10);
		// 3: Comprobar los resultados
		//Devolvemos un matchDTO con id 10, al que le hemos pasado un DTO diferente, por lo tanto, estamos devolviendo otro DTO.
		assertNotNull("El objeto result es null", result);
		assertEquals("El resultado es diferente de MOCK", "MOCK", result.getResultado());
		assertEquals(2, result.getIdLocal());
		verify(service).findById(Mockito.eq(10));
	}
	
	
	@Test
	public void readAllTest() {
		// 1: Definir el comportamiento de las dependencias externas (stubbing)
		//Aqui indicamos que cuando se ejecute el findAll, devuelva una lista (como hace el metodo original al que no estamos accediendo desde aqui)
		when(service.findAll()).thenReturn(new ArrayList<MatchDTO>());
		// 2: Invocar al metodo de la clase que queremos probar
		List<MatchDTO> result = controller.readAll();
		// 3: Comprobar los resultados
		assertNotNull("El objeto result es null", result);
		assertThat("La lista no coincide",result.size(), is(new ArrayList<MatchDTO>().size()));
        assertThat(result, not(result.isEmpty()));
		assertEquals(result.size(), 0);
		verify(service, times(1)).findAll();
	}
	
	
	//Tests de inserción/modificación de datos
	
	@Test
	public void createTest() {
		MatchDTO dto = new MatchDTO();
		when(service.create(dto)).thenReturn(new MatchDTO());
		MatchDTO result = controller.create(dto);

		//Asignamos atributos a un MatchDTO para despues comprobarlo
		result.setIdLocal(3);
		result.setIdVisitante(2);
		result.setResultado("LOCAL");		
		
		assertNotNull("El objeto result es null", result);
		assertEquals(dto.getIdMatch(), result.getIdMatch());
		assertEquals("El resultado no es LOCAL", "LOCAL", result.getResultado());
						
		//Verificamos que el metodo create reciba un tipo matchDTO
		verify(service).create(Mockito.any(MatchDTO.class));
		
		//ArgumentCaptor para verificar atributos
		
	}
	
	@Test
	public void updateMatchTest() {
		MatchDTO dto = new MatchDTO();
		dto.setIdLocal(1);
		dto.setIdVisitante(2);
		dto.setResultado("LOCAL");
		MatchDTO mockedResult = new MatchDTO();
		mockedResult.setResultado("LOCAL");
		when(service.updateMatch(dto)).thenReturn(mockedResult);
		
		MatchDTO result = controller.updateMatch(dto);
		
		assertEquals("El resultado updateado no corresponde", "LOCAL", result.getResultado());

		assertNotNull("El objeto result es null", result);
		
		verify(service).updateMatch(Mockito.any(MatchDTO.class));
		
	}
	
	
	//IMPLEMENTAR TEST BORRADO
	
	@Test
	public void deleteTest_matchNotFound() {
		//definicion
		when(service.findById(Mockito.eq(1))).thenReturn(null);
		//invocar
		controller.delete(1);
		//verificar
		verify(service, times(1)).findById(1);
		verify(service, times(0)).deleteMatchById(Mockito.anyInt());
	}
	
	@Test
	public void deleteTest_matchFound() {
		//definicion
		MatchDTO match = new MatchDTO();
		when(service.findById(Mockito.eq(1))).thenReturn(match);
		//invocar
		controller.delete(1);
		//verificar
		verify(service, times(1)).findById(1);
		verify(service, times(1)).deleteMatchById(1);
		
	}
	
	//TESTS DE LOS METODOS ADICIONALES
	
	@Test
	public void findByResultLocalVisitanteNotFoundTest() {
		
		//En caso de que el segundo parametro no sea Local ni Visitante, habrá 0 interacciones con el service y devolverá null.
		
		//when(service.findByResultLocal("MOCK", "WHATELSE")).thenReturn(null);
		
		List<MatchDTO> result = controller.findByResultLocalVisitante("MOCK", "WHATELSE");
		
		assertNull("El objeto result no es null", result);
		
		verify(service, times(0)).findByResultLocal("MOCK", "WHATELSE");
		
	}
	
	@Test
	public void findByResultLocalVisitanteLTest() {
		
		//Definición(mock)
		when(service.findByResultLocal("MOCK", "Local")).thenReturn(new ArrayList<MatchDTO>());
		
		//Invocar (controller)
		List<MatchDTO> result = controller.findByResultLocalVisitante("MOCK", "Local");
		
		//Comprobaciones
		assertNotNull("El objeto result es null", result);
		
		assertEquals("La lista no coincide",result.size(), 0);

		verify(service, times(1)).findByResultLocal("MOCK", "Local");
		
	}
	
	@Test
	public void findByResultLocalVisitanteVTest() {
		
		//Definición(mock)
		when(service.findByResultVisitante("MOCK", "Visitante")).thenReturn(new ArrayList<MatchDTO>());
		
		//Invocar (controller)
		List<MatchDTO> result = controller.findByResultLocalVisitante("MOCK", "Visitante");
		
		//Comprobaciones
		assertNotNull("El objeto result es null", result);
		
		assertThat("La lista no coincide",result.size(), is(new ArrayList<MatchDTO>().size()));
        assertThat(result, not(result.isEmpty()));
		verify(service, times(1)).findByResultVisitante("MOCK", "Visitante");
		
	}
	
	@Test
	public void findMatchByResultTest() {
		
		//Captura de argumentos para extraer variables dentro del método
		ArgumentCaptor<PageRequestDto> argumentCaptor = ArgumentCaptor.forClass(PageRequestDto.class);
		//Definición
		when(service.findMatchByResult(Mockito.eq("LOCAL"), Mockito.any(PageRequestDto.class) )).thenReturn(new PageableMatchDTO());
		
		//Invocación
		PageableMatchDTO result = controller.findMatchByResult("LOCAL", 10, 1);
		
		//Comprobaciones
			assertNotNull("El objeto es null", result);
		
		verify(service, times(1)).findMatchByResult(Mockito.eq("LOCAL"), argumentCaptor.capture());
		
		//Comprobación de los argumentos capturados.
		assertThat(argumentCaptor.getValue().getLimit()).isEqualTo(10);
		
	}

}
