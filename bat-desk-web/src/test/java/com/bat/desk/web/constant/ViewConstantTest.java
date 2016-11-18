package com.bat.desk.web.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.bat.architecture.testing.util.JUnitTestBuilder;

public class ViewConstantTest {

	@Test
	public void checkWellFormattedClass() throws NoSuchMethodException, InvocationTargetException,
			InstantiationException, IllegalAccessException {
		JUnitTestBuilder.assertUtilityClassWellDefined(ViewConstant.class);
	}

}
