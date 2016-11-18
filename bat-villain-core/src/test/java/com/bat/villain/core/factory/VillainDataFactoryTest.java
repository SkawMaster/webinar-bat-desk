package com.bat.villain.core.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.bat.villain.core.constant.VillainConstant;
import com.bat.villain.core.entity.Villain;

public class VillainDataFactoryTest {

	private final int NUM_ELEMENTS = 2;

	@Before
	public void init() {
	}

	@Test
	public void shouldCreateSampleVillain() {
		assertNotNull(VillainDataFactory.createWithId(VillainConstant.TEST_ID,VillainConstant.TEST_NAME, VillainConstant.TEST_DESCRIPTION));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void shouldCreateSampleVillainConstructor() {
		assertNotNull(new VillainDataFactory().createWithId(VillainConstant.TEST_ID,VillainConstant.TEST_NAME, VillainConstant.TEST_DESCRIPTION));
	}
	
	@Test
	public void shouldCreateSampleVillainDefault() {
		assertNotNull(VillainDataFactory.createDefault());
	}

	@Test
	public void shouldCreateSampleVillainrMap() {
		Map<Long, Villain> result = VillainDataFactory.createMap();
		
		assertNotNull(result);
		assertEquals(NUM_ELEMENTS,result.size());
	}
	
	@Test
	public void shouldCreateSampleVillainList() {
		List<Villain> result = VillainDataFactory.createList();
		
		assertNotNull(result);
		assertEquals(NUM_ELEMENTS,result.size());
	}

}