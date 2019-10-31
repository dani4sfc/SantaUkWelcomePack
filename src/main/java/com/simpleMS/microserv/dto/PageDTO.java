package com.simpleMS.microserv.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

//private PageablePlayerDTO pagination;

@Data
public class PageDTO{

//public class PageRequestDto extends RedesDto {
//
	
	  /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6788622796690532324L;

    /** The results. */
    private List<PageDTO> results;

    /** Datos para la paginación. */
	private PageablePlayerDTO pagination;
//	
///////////////////////////////////////////////////////////
	
	
	
/** The Constant serialVersionUID. */
//private static final long serialVersionUID = 2642427949222053008L;

/** Page number. */
@NotNull(message = "Field {field} is mandatory")
@PositiveOrZero(message = "Field {field} should be positive or zero.")
private int page;

/** max register num. */
@NotNull(message = "Field {field} is mandatory")
@PositiveOrZero(message = "Field {field} should be positive or zero.")
private int limit;

/** The length. */
@JsonProperty(access = Access.READ_ONLY)
private int total;

}