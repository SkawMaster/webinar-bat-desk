package com.bat.villain.core.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bat.desk.common.exception.BatDeskException;
import com.bat.villain.core.entity.Villain;
import com.bat.villain.core.enumerate.VillainStatusEnum;
import com.bat.villain.core.exception.enumerate.VillainTypeExceptionEnum;
import com.bat.villain.core.repository.VillainMapper;
import com.bat.villain.core.service.VillainOperationService;
import com.bat.villain.core.validation.VillainValidation;

@Service("villainOperationService")
public class VillainOperationServiceImpl implements VillainOperationService {

	private final Logger LOG = LoggerFactory.getLogger(VillainServiceImpl.class);
	
	@Autowired
	private VillainMapper villainMapper;

	private void generateNotFoundException(Villain villain) throws BatDeskException {
		if (!VillainValidation.INSTANCE.isValid(villain)) {
			throw new BatDeskException(VillainTypeExceptionEnum.NOT_FOUND.name());
		}
	}
	
	@Override
	public void detain(long villainId) throws BatDeskException {
		LOG.trace("Detaining Villain with id : {}", villainId);
		
		final Villain villain = villainMapper.findByPK(villainId);
		
		generateNotFoundException(villain);
		
		if (VillainValidation.INSTANCE.isDead(villain)){
			throw new BatDeskException(VillainTypeExceptionEnum.IS_DEAD.name());
		}
		
		if (VillainValidation.INSTANCE.isDetained(villain)){
			throw new BatDeskException(VillainTypeExceptionEnum.IS_DETAINED.name());
		}
		
		villain.setStatus(VillainStatusEnum.DETAINED);
		
		if (villainMapper.update(villain) == 0) {
			throw new BatDeskException(VillainTypeExceptionEnum.DB_ERROR.name());
		}
		
		LOG.trace("Villain detained with id : {}", villainId);
	}
	
	public void setVillainMapper(VillainMapper villainMapper) {
		this.villainMapper = villainMapper;
	}
	
}
