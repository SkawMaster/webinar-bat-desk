package com.bat.villain.core.service;

import java.util.List;

import com.bat.villain.core.entity.Villain;

public interface VillainService {

	void insert(Villain villain);

	void update(Villain villain);
	
	void delete(Long id);
	
	List<Villain> findAll();
	
	Villain findByPK(Long id);

}
