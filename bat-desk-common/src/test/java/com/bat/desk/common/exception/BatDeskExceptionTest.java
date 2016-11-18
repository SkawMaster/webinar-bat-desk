package com.bat.desk.common.exception;

import com.bat.architecture.testing.exception.util.AbstractExceptionTestUtil;
import com.bat.desk.common.exception.BatDeskException;


public class BatDeskExceptionTest extends AbstractExceptionTestUtil {

	@Override
	protected Exception getExceptionWithParameter() {
		return new BatDeskException("1");
	}

}
