package com.simpleMS.microserv.dto;

import java.util.List;

import com.simpleMS.microserv.util.PageRequestDto;

import lombok.Data;


@Data
public class PageableMatchDTO {
	
	List<MatchDTO> content;
	
	PageRequestDto page;

}
