package com.bat.villain.core.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.bat.architecture.testing.util.JUnitTestBuilder;
import com.bat.villain.core.constant.VillainConstant;

public class VillainConstantTest {

	@Test
	public void checkWellFormattedClass() throws NoSuchMethodException, InvocationTargetException,
			InstantiationException, IllegalAccessException {
		JUnitTestBuilder.assertUtilityClassWellDefined(VillainConstant.class);
	}

}
