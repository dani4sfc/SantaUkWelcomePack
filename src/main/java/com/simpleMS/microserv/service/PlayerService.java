package com.simpleMS.microserv.service;

import java.util.List;

import com.simpleMS.microserv.dto.PageablePlayerDTO;
import com.simpleMS.microserv.dto.PlayerDTO;
import com.simpleMS.microserv.util.PageRequestDto;

public interface PlayerService {
	
	public PlayerDTO findById(int id);
	
	public List<PlayerDTO> findAll();
	
	public PlayerDTO create (PlayerDTO dto);
	
	public PlayerDTO updatePlayer(PlayerDTO dto);
	
	public void deletePlayerById(int id);
	
	public List<PlayerDTO> selectAllPlayersOrder();
	
	public PageablePlayerDTO selectAllPlayersOrderPage(PageRequestDto pageReq);
	

}
