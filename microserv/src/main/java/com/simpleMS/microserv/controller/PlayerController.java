package com.simpleMS.microserv.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simpleMS.microserv.dto.PageablePlayerDTO;
import com.simpleMS.microserv.dto.PlayerDTO;
import com.simpleMS.microserv.service.PlayerService;
import com.simpleMS.microserv.util.PageRequestDto;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	private PlayerService service;

	// --------------GET----------
	@GetMapping(path = "/{id}")
	public PlayerDTO readById(@PathVariable(value = "id") int id) {
		return service.findById(id);

	}

	// ---------------GETALL-------------
	@GetMapping(path = "/findAll")
	public List<PlayerDTO> readAll() {
		return service.findAll();

	}

	// QUERY ORDER Y PAGINATION
	@GetMapping(path = "/findAllOrder")
	public List<PlayerDTO> selectAllPlayersOrder() {
		return service.selectAllPlayersOrder();

	}

	//  PAGINACION
	@GetMapping(path = "/findAllOrderPage")
	public PageablePlayerDTO selectAllPlayersOrderPage(@RequestParam("limit") Integer limit, @RequestParam("page") Integer page) {
		PageRequestDto pageReq =  new PageRequestDto(page, limit, null);
		return service.selectAllPlayersOrderPage(pageReq);
	}

	// -------------CREATE-------------
	@PostMapping(path = "/createPlayers", consumes = "application/json")
	public PlayerDTO create(@RequestBody PlayerDTO dto) {
		return service.create(dto);
	}

	// -------------UPDATE--------------
	@PutMapping(path = "/updatePlayers", consumes = "application/json")
	public PlayerDTO updatePlayer(@RequestBody PlayerDTO input) {

		return service.updatePlayer(input);

	}

	// ------------DELETE-------------
	@DeleteMapping(path = "/deletePlayers/{id}")
	public void deletePlayer(@PathVariable(value = "id") int id) {
		service.deletePlayerById(id);
	}
}
