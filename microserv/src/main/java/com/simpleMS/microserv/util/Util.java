package com.simpleMS.microserv.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Util {

	
	public static Pageable getPageable(PageRequestDto datosPaginacion) {
        int numPag = datosPaginacion.getPage();
        int numRegistros = datosPaginacion.getLimit();
        return numPag == 0 && numRegistros == 0 ? PageRequest.of(0, 10) : PageRequest.of(numPag, numRegistros);
    } 
	
	public static Pageable getSortedPageable(PageRequestDto datosPaginacion, String sortedBy) {
        int numPag = datosPaginacion.getPage();
        int numRegistros = datosPaginacion.getLimit();
        return numPag == 0 && numRegistros == 0 ? PageRequest.of(0, 10, Sort.by(sortedBy)) : PageRequest.of(numPag, numRegistros, Sort.by(sortedBy));
    } 
	
}
