package com.bat.desk.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.bat.desk.web.constant.ParameterConstant;
import com.bat.desk.web.constant.ViewConstant;

public class NavigationControllerTest {

	private NavigationController navigationController;

	private Model model;

	@Before
	public final void setUp() throws Exception {
		model = new ExtendedModelMap();

		navigationController = spy(new NavigationController());
	}

	@Test
	public final void testGoToIndexView() {
		String view = navigationController.goToIndexView(model);
		
		assertEquals(ViewConstant.HOME_VIEW, view);
	}
	
	@Test
	public final void testGoToHomeView() {	
		final String view = navigationController.goToHomeView(model);
		final Date attribute = (Date) model.asMap().get(ParameterConstant.PARAM_CONNECTION_TIME);
		
		assertEquals(ViewConstant.HOME_VIEW,view);
		assertNotNull(attribute);	
	}
	
	@Test
	public final void testGoToErrorView() {
		String view = navigationController.goToErrorView();
		
		assertEquals(ViewConstant.ERROR_VIEW, view);
	}
	
	@Test
	public final void testGoToLoginView() {
		String view = navigationController.goToLoginView(model);
		
		assertEquals(ViewConstant.LOGIN_VIEW, view);
	}

}
