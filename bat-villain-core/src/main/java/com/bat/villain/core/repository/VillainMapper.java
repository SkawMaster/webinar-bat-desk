package com.bat.villain.core.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bat.villain.core.entity.Villain;

@Component
public interface VillainMapper {
	
	int insert(Villain villain);
	
	int remove(Long id);
	
	int update(Villain villain);
	
	List<Villain> findAll();
	
	Villain findByPK(Long id);
	 
}
