package com.bat.villain.core.enumerate;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.bat.architecture.testing.util.JUnitTestBuilder;

public class VillainStatusEnumTest {

	@Test
	public void checkValueOfUIElementEnum() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		JUnitTestBuilder.superficialEnumCodeCoverage(VillainStatusEnum.class);
	}

}
