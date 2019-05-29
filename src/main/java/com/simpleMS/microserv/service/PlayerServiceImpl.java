package com.simpleMS.microserv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.simpleMS.microserv.dto.PageablePlayerDTO;
import com.simpleMS.microserv.dto.PlayerDTO;
import com.simpleMS.microserv.entity.PlayerEntity;
import com.simpleMS.microserv.mapper.PlayerMapper;
import com.simpleMS.microserv.repository.PlayerRepository;
import com.simpleMS.microserv.util.PageRequestDto;
import com.simpleMS.microserv.util.Util;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerRepository repository;

	@Autowired
	PlayerMapper mapper;

	public PlayerDTO findById(int id) {
		return mapper.toDTONoRelationPlayer(repository.findById(id).get());
	}

	public List<PlayerDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}

	public PlayerDTO create(PlayerDTO dto) {

		return mapper.toDTO(repository.save(mapper.toEntity(dto)));

	}

	public PlayerDTO updatePlayer(PlayerDTO input) {
		PlayerDTO dtoBBDD = this.findById(input.getIdPlayer());
		PlayerDTO result = new PlayerDTO();
		if (dtoBBDD != null) {
			PlayerEntity toUpdateEntity = mapper.toEntity(input);
			result = mapper.toDTO(repository.save(toUpdateEntity));
		}
		return result;
	}

	public void deletePlayerById(int id) {
		repository.deleteById(id);
	}

	public List<PlayerDTO> selectAllPlayersOrder() {
		return mapper.toDTO(repository.selectAllPlayersOrder());

		// result.setPage(page.getPageable());

	}

	public PageablePlayerDTO selectAllPlayersOrderPage(PageRequestDto pageReq) {
		Pageable pageable = Util.getSortedPageable(pageReq, "name");
		
		Page<PlayerEntity> page = repository.findAll(pageable);
		PageablePlayerDTO result = new PageablePlayerDTO();
		result.setContent(mapper.toDTO(page.getContent()));
		result.setPage(new PageRequestDto(page.getPageable().getPageNumber(), page.getPageable().getPageSize(),
				Math.toIntExact(page.getTotalElements())));
		return result;
	}

//	@Override
//	public List<PlayerDTO> findAllOrderById() {
//		// TODO Auto-generated method stub
//		return mapper.toDTO(repository.findByNameOrderByIdPlayer());
//	}
}
