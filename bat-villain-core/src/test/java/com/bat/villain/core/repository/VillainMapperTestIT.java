package com.bat.villain.core.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bat.villain.core.constant.VillainConstant;
import com.bat.villain.core.entity.Villain;
import com.bat.villain.core.factory.VillainDataFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:spring-configuration/core-context-test.xml"})
//@Transactional(value = "villainTransactionManager")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("development")
public class VillainMapperTestIT {

	@Resource
	VillainMapper villainMapper;
	private Integer NUM_ELEMENTS = 6;
	private Villain villainTest;

	@Before
	public void init() {
		villainTest = VillainDataFactory.createDefault();
	}

	@Test
	public void shouldRetrieveAll() {
		final List<Villain> result = villainMapper.findAll();
		assertNotNull(result);
		assertEquals(NUM_ELEMENTS, Integer.valueOf(result.size()));
	}
	
	@Test
	public void shouldRetrieveVillainById() {
		final Villain result = villainMapper.findByPK(VillainConstant.TEST_ID);
		
		assertNotNull(result);
		assertEquals(VillainConstant.TEST_ID, result.getId());
	}
	
	@Test
	public void shouldInsertVillain() {
		final Long newID = Long.valueOf((NUM_ELEMENTS+1));
		
		villainTest.setId(-1L);
		int res = villainMapper.insert(villainTest);
		
		assertNotSame(0, res);	
		assertEquals(newID, villainTest.getId());
	}
	
	@Test
	public void shouldUpdateVillain() {
		String descriptionValue = "Test description";
		villainTest.setDescription(descriptionValue);
		int res = villainMapper.update(villainTest);

		assertNotSame(0, res);
		final Villain result = villainMapper.findByPK(VillainConstant.TEST_ID);
		
		assertNotNull(result);
		assertEquals(descriptionValue, result.getDescription());
	}
	
	@Test
	public void shouldDeleteVillain() {
		int res = villainMapper.remove(VillainConstant.TEST_ID);

		assertNotSame(0, res);
		
		final Villain result = villainMapper.findByPK(VillainConstant.TEST_ID);
		assertNull(result);
		
	}
	
}