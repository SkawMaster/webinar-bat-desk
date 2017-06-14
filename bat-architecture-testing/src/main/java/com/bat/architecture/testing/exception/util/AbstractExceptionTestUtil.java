package com.bat.architecture.testing.exception.util;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractExceptionTestUtil {

	private static final String PHRASE_END = ".";

	private static final String EMPTY_STRING = "";
	
	private Exception exception;

	protected abstract Exception getExceptionWithParameter();
	
	@Before
	public void beforeTest() {
		exception = getExceptionWithParameter();
	}

	@Test
	public void shouldGetMessageWithEmptyConstructor() throws InstantiationException, IllegalAccessException {
		Matcher<String> matcher = containsString(exception.getMessage().replace(PHRASE_END, EMPTY_STRING));
		assertMessage(exception.getMessage(), matcher);
	}

	private void assertMessage(String message, Matcher<String> matcher) {
		assertThat(message, matcher);
	}
	
}
