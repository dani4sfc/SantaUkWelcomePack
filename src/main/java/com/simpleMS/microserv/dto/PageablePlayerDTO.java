package com.simpleMS.microserv.dto;

import java.util.List;

import com.simpleMS.microserv.util.PageRequestDto;

import lombok.Data;

@Data
public class PageablePlayerDTO {
	
	List<PlayerDTO> content;
	
	PageRequestDto page;
	

}
