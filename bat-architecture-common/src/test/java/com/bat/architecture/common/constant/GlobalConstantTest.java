package com.bat.architecture.common.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.bat.architecture.testing.util.JUnitTestBuilder;

public class GlobalConstantTest {

	@Test
	public void checkWellFormattedClass() throws NoSuchMethodException, InvocationTargetException,
			InstantiationException, IllegalAccessException {
		JUnitTestBuilder.assertUtilityClassWellDefined(GlobalConstant.class);
	}

}
