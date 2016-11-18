package com.bat.villain.core.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.bat.villain.core.constant.VillainConstant;
import com.bat.villain.core.factory.VillainDataFactory;

public class VillainTest {
	
	private Villain villain;

	private Villain anotherVillain;

	private Villain cloneVillain;
	
	@Before
	public void init() {
		villain = VillainDataFactory.createDefault();
		cloneVillain =  VillainDataFactory.createDefault();
		anotherVillain = VillainDataFactory.createDefault();
		anotherVillain.setId(2L);
	}
	
	@Test
	public void equalsMethodCheckTheType() {
		assertFalse(villain.equals("a string"));
	}

	@Test
	public void equalsMehtodCheckSameObject() throws Exception {
		assertTrue(villain.equals(villain));
	}
	
	@Test
	public void equalsMehtodCheckIdField() throws Exception {
		assertFalse(villain.equals(anotherVillain));
	}

	@Test
	public void equalsMehtodCheckIdFieldEquals() throws Exception {
		assertTrue(villain.equals(cloneVillain));
	}

	@Test
	public void hashproductMethodBasedInTheIDField() throws Exception {
		assertNotSame(villain.hashCode(), anotherVillain.hashCode());
	}

	@Test
	public void shouldHaveADescriptiveToStringMethod() {
		assertNotSame(-1, villain.toString().indexOf(Villain.class.getSimpleName()));
		assertNotSame(-1,
				villain.toString().indexOf("id=".concat(VillainConstant.TEST_ID.toString())));
	}

	@Test
	public void shouldHasMethodAccessors() {
		assertNotNull(villain.getId());
		assertNotNull(villain.getName());
		assertNotNull(villain.getDescription());
		assertNotNull(villain.getStatus());
		assertNotNull(villain.getCreationDate());
		assertNull(villain.getModificationDate());
	}

}
