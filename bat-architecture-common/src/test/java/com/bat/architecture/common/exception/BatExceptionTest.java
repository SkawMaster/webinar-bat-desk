package com.bat.architecture.common.exception;

import com.bat.architecture.testing.exception.util.AbstractExceptionTestUtil;


public class BatExceptionTest extends AbstractExceptionTestUtil {

	@Override
	protected Exception getExceptionWithParameter() {
		return new BatException("1");
	}

}
