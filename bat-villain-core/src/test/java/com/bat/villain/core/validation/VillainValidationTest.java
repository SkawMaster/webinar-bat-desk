package com.bat.villain.core.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;

import com.bat.architecture.testing.util.JUnitTestBuilder;
import com.bat.villain.core.entity.Villain;
import com.bat.villain.core.enumerate.VillainStatusEnum;
import com.bat.villain.core.factory.VillainDataFactory;

public class VillainValidationTest {

	private Villain villainTest;

	@Before
	public void init() {
		villainTest	 = VillainDataFactory.createDefault();
	}

	@Test
	public void shouldIsNull() {
		assertTrue(VillainValidation.INSTANCE.isNull(null));
	}

	@Test
	public void shouldIsNullWithVillainNotNull() {
		assertFalse(VillainValidation.INSTANCE.isNull(villainTest));
	}

	@Test
	public void shouldIsNotNull() {
		assertTrue(VillainValidation.INSTANCE.isNotNull(villainTest));
	}

	@Test
	public void shouldIsNotNullWithVillainNull() {
		assertFalse(VillainValidation.INSTANCE.isNotNull(null));
	}

	@Test
	public void shouldIsValidWithVillainNull() {
		assertFalse(VillainValidation.INSTANCE.isValid(null));
	}

	@Test
	public void shouldIsValidWithIdVillainNull() {
		villainTest.setId(null);
		assertFalse(VillainValidation.INSTANCE.isValid(villainTest));
	}

	@Test
	public void shouldIsValid() {
		assertTrue(VillainValidation.INSTANCE.isValid(villainTest));
	}
	
	@Test
	public void shouldIsDetainedWithVillainNull() {
		assertFalse(VillainValidation.INSTANCE.isDetained(null));
	}
	
	@Test
	public void shouldIsDetainedWithVillainNoDetained() {
		assertFalse(VillainValidation.INSTANCE.isDetained(villainTest));
	}
	
	@Test
	public void shouldIsDetained() {
		villainTest.setStatus(VillainStatusEnum.DETAINED);
		assertTrue(VillainValidation.INSTANCE.isDetained(villainTest));
	}
	
	@Test
	public void shouldIsDeadWithVillainNull() {
		assertFalse(VillainValidation.INSTANCE.isDead(null));
	}
	
	@Test
	public void shouldIsDeadWithVillainNoDead() {
		assertFalse(VillainValidation.INSTANCE.isDead(villainTest));
	}
	
	@Test
	public void shouldIsDead() {
		villainTest.setStatus(VillainStatusEnum.DEAD);
		assertTrue(VillainValidation.INSTANCE.isDead(villainTest));
	}
	
	@Test
	public void checkValueOfValidationUtils() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		JUnitTestBuilder.superficialEnumCodeCoverage(VillainValidation.class);
	}

}