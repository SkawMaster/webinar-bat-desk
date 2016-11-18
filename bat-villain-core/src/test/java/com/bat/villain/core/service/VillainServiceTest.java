package com.bat.villain.core.service;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.bat.villain.core.entity.Villain;
import com.bat.villain.core.factory.VillainDataFactory;
import com.bat.villain.core.repository.VillainMapper;
import com.bat.villain.core.service.impl.VillainServiceImpl;


public class VillainServiceTest {
	
	private VillainServiceImpl villainService;
	
	private VillainMapper villainMapper;
	
	private Villain villainTest;

	@Before
	public void initTests() {
		villainTest = VillainDataFactory.createDefault();
				
		villainService = new VillainServiceImpl();
		villainMapper = mock(VillainMapper.class);
		
		when(villainMapper.findByPK(anyLong())).thenReturn(villainTest);
		
		villainService.setVillainMapper(villainMapper);
	}
	
	@Test
	public void whenCallInsertThenInvokeTheDaoMethod() {
		villainService.insert(villainTest);
		verify(villainMapper).insert(villainTest);
	}
	
	@Test
	public void whenCallUpdateThenInvokeTheDaoMethod() {
		villainService.update(villainTest);
		verify(villainMapper).update(villainTest);
	}
	
	@Test
	public void whenCallFindAllElementsThenInvokeTheDaoMethod() {
		villainService.findAll();
		verify(villainMapper).findAll();
	}
	
	@Test
	public void whenCallFindThenInvokeTheDaoMethod() {
		villainService.findByPK(villainTest.getId());
		verify(villainMapper).findByPK(villainTest.getId());
	}
	
	@Test
	public void whenCallRemoveThenInvokeTheDaoMethod() {
		villainService.delete(villainTest.getId());
		verify(villainMapper).remove(villainTest.getId());
	}

}
