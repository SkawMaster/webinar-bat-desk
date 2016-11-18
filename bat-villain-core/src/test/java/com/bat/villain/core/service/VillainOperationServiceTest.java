package com.bat.villain.core.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.bat.desk.common.exception.BatDeskException;
import com.bat.villain.core.constant.VillainConstant;
import com.bat.villain.core.entity.Villain;
import com.bat.villain.core.enumerate.VillainStatusEnum;
import com.bat.villain.core.factory.VillainDataFactory;
import com.bat.villain.core.repository.VillainMapper;
import com.bat.villain.core.service.impl.VillainOperationServiceImpl;


public class VillainOperationServiceTest {
	
	private VillainOperationServiceImpl villainOperationService;
	
	private VillainMapper villainMapper;
	
	private Villain villainTest;

	@Before
	public void initTests() {
		villainTest = VillainDataFactory.createDefault();
				
		villainOperationService = new VillainOperationServiceImpl();
		villainMapper = mock(VillainMapper.class);
		
		when(villainMapper.findByPK(anyLong())).thenReturn(villainTest);
		
		villainOperationService.setVillainMapper(villainMapper);
	}

//  Option 1
//	@Test
//	public void shouldDetainNotFoundException() {
//		when(villainMapper.findByPK(anyLong())).thenReturn(null);
//		boolean existException = false;
//		
//		try {
//			villainOperationService.detain(VillainConstant.TEST_ID);
//		} catch (BatDeskException e) {
//			existException = true;
//		}
//		
//		assertTrue(existException);
//	}
	
	@Test (expected=BatDeskException.class)
	public void shouldDetainNotFoundException() throws BatDeskException {
		when(villainMapper.findByPK(anyLong())).thenReturn(null);
		
		villainOperationService.detain(VillainConstant.TEST_ID);
	}	
	
	@Test (expected=BatDeskException.class)
	public void shouldDetainWithDeadStatusException() throws BatDeskException {
		villainTest.setStatus(VillainStatusEnum.DEAD);
		
		villainOperationService.detain(VillainConstant.TEST_ID);
	}
	
	@Test (expected=BatDeskException.class)
	public void shouldDetainWithDetainStatusException() throws BatDeskException {
		villainTest.setStatus(VillainStatusEnum.DETAINED);
		
		villainOperationService.detain(VillainConstant.TEST_ID);
	}
	
	@Test (expected=BatDeskException.class)
	public void shouldDetainWithErrorDBException() throws BatDeskException {
		villainTest.setStatus(VillainStatusEnum.FREE);
		when(villainMapper.update(villainTest)).thenReturn(0);
		
		villainOperationService.detain(VillainConstant.TEST_ID);
	}
	
	@Test
	public void shouldDetain() throws BatDeskException {
		villainTest.setStatus(VillainStatusEnum.FREE);
		when(villainMapper.update(villainTest)).thenReturn(1);
		
		villainOperationService.detain(VillainConstant.TEST_ID);
		
		assertEquals(VillainStatusEnum.DETAINED,villainTest.getStatus());
	}	
}
