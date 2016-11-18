package com.bat.villain.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bat.villain.core.entity.Villain;
import com.bat.villain.core.repository.VillainMapper;
import com.bat.villain.core.service.VillainService;

@Service("villainService")
public class VillainServiceImpl implements VillainService, Serializable {

	private static final long serialVersionUID = 3352742946434440020L;
	
	private final Logger LOG = LoggerFactory.getLogger(VillainServiceImpl.class);
	
	@Autowired
	private VillainMapper villainMapper;

	@Override
	public void insert(Villain villain) {
		LOG.trace("Insert Villain");
		villainMapper.insert(villain);	
	}
	
	@Override
	public void update(Villain villain) {
		LOG.trace("Update Villain with id : {}", villain.getId());
		villainMapper.update(villain);
	}

	@Override
	public List<Villain> findAll() {
		LOG.trace("Find all Villains");
		return villainMapper.findAll();
	}
	
	@Override
	public Villain findByPK(Long id) {
		LOG.trace("Find by id : {}", id);
		return villainMapper.findByPK(id);
	}
	
	@Override
	public void delete(Long id) {
		LOG.trace("Delete by id : {}", id);
		villainMapper.remove(id);
	}

	public void setVillainMapper(VillainMapper villainMapper) {
		this.villainMapper = villainMapper;
	}

}
