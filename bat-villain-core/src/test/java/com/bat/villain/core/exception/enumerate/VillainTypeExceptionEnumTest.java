package com.bat.villain.core.exception.enumerate;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.bat.architecture.testing.util.JUnitTestBuilder;

public class VillainTypeExceptionEnumTest {

	@Test
	public void checkValueOfEnum() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		JUnitTestBuilder.superficialEnumCodeCoverage(VillainTypeExceptionEnum.class);
	}

}
