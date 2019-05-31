package com.simpleMS.microserv.util;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDto {

	    /** The Constant serialVersionUID. */
	    private static final long serialVersionUID = 2642427949222053008L;

	    /** Número de página. */
	    @NotNull(message = "Field {field} is mandatory")
	    @PositiveOrZero(message = "Field {field} should be positive or zero.")
	    private Integer page;

	    /** Número máximo de registros. */
	    @NotNull(message = "Field {field} is mandatory")
	    @PositiveOrZero(message = "Field {field} should be positive or zero.")
	    private Integer limit;

	    /** The length. */
	    @JsonProperty(access = Access.READ_ONLY)
	    private Integer total;

}
