package com.bat.desk.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;


import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bat.desk.common.exception.BatDeskException;
import com.bat.desk.web.constant.ParameterConstant;
import com.bat.desk.web.constant.ViewConstant;
import com.bat.villain.core.constant.VillainConstant;
import com.bat.villain.core.entity.Villain;
import com.bat.villain.core.factory.VillainDataFactory;
import com.bat.villain.core.service.VillainOperationService;
import com.bat.villain.core.service.VillainService;

public class VillainControllerTest {
	
	private final String MESSAGE_SOURCE_VALUE = "TEST";

	private VillainController villainController;

	private VillainService villainService;
	
	private VillainOperationService villainOperationService;
	
	private RedirectAttributes redirectAttributes;

	private List<Villain> villainListTest;
	
	private Villain villainTest;
	
	private Model model;
	
	private HttpServletRequest request;
	
	private MessageSource messageSource;
	
	@Before
	public final void setUp() throws Exception {
		villainListTest = VillainDataFactory.createList();
		villainTest = VillainDataFactory.createDefault();
		model = new ExtendedModelMap();

		villainController = spy(new VillainController());
		villainService = mock(VillainService.class);
		villainOperationService = mock(VillainOperationService.class);
		
		redirectAttributes = mock(RedirectAttributes.class);
		request = mock(HttpServletRequest.class);
		messageSource = mock(MessageSource.class);
		
		villainController.setVillainService(villainService);
		villainController.setVillainOperationService(villainOperationService);
		villainController.setMessageSource(messageSource);
		
		when(messageSource.getMessage(anyString(), any(Object[].class), anyObject())).thenReturn(MESSAGE_SOURCE_VALUE);
		when(villainService.findByPK(anyLong())).thenReturn(villainTest);
	}

	@Test
	public final void shouldBeVillainListPage() {
		when(villainService.findAll()).thenReturn(villainListTest);
		
		final String view = villainController.list(model);
		final Collection<?> villainAttribute = (Collection<?>) model.asMap().get(ParameterConstant.PARAM_LIST);
		
		assertEquals(ViewConstant.VILLAIN_FOLDER + ViewConstant.VILLAIN_LIST_VIEW, view);
		assertFalse(villainAttribute.isEmpty());	
	}
	
	@Test
	public final void shouldBeShowEditWithError() {
		when(villainService.findByPK(anyLong())).thenReturn(null);
		
		final ModelAndView modelAndView = villainController.showEdit(VillainConstant.TEST_ID, model, request);
		final String message = (String) modelAndView.getModel().get(ParameterConstant.PARAM_EXCEPTION);
		final Villain villain = (Villain) modelAndView.getModel().get(ParameterConstant.PARAM_VILLAIN);
		
		assertEquals(ViewConstant.ERROR_VIEW,modelAndView.getViewName());
		assertEquals(MESSAGE_SOURCE_VALUE,message);
		assertNull(villain);
	}
	
	@Test
	public final void shouldBeShowEdit() {
		final ModelAndView modelAndView = villainController.showEdit(VillainConstant.TEST_ID, model, request);
		final Villain villain = (Villain) modelAndView.getModel().get(ParameterConstant.PARAM_VILLAIN);
		
		assertEquals(ViewConstant.VILLAIN_FOLDER + ViewConstant.VILLAIN_EDIT_VIEW,modelAndView.getViewName());
		assertNotNull(villain);
	}
	
	@Test
	public final void shouldBeEdit() {
		final String view = villainController.edit(villainTest, request, redirectAttributes);
		
		assertEquals(ViewConstant.REDIRECT_LIST_REQUEST_MAPPING,view);
	}
	
	@Test
	public final void shouldBeShowAdd() {
		final String view = villainController.showAdd(model);
		final Villain villain = (Villain) model.asMap().get(ParameterConstant.PARAM_VILLAIN);
		
		assertEquals(ViewConstant.VILLAIN_FOLDER + ViewConstant.VILLAIN_ADD_VIEW,view);
		assertNotNull(villain);
		assertNull(villain.getId());
	}
	
	@Test
	public final void shouldBeAdd() {
		final String view = villainController.add(villainTest, request, redirectAttributes);
		
		assertEquals(ViewConstant.REDIRECT_LIST_REQUEST_MAPPING,view);
	}
	
	@Test
	public final void shouldBeDeleteWithError() {
		when(villainService.findByPK(anyLong())).thenReturn(null);
		
		final ModelAndView modelAndView = villainController.delete(VillainConstant.TEST_ID, request, redirectAttributes);
		final String message = (String) modelAndView.getModel().get(ParameterConstant.PARAM_EXCEPTION);
		
		assertEquals(ViewConstant.ERROR_VIEW,modelAndView.getViewName());
		assertEquals(MESSAGE_SOURCE_VALUE,message);
	}
	
	@Test
	public final void shouldBeDelete() {
		final ModelAndView modelAndView = villainController.delete(VillainConstant.TEST_ID, request, redirectAttributes);
		
		assertEquals(ViewConstant.REDIRECT_LIST_REQUEST_MAPPING,modelAndView.getViewName());
	}
	
	@Test
	public final void shouldBeInitializeVillainStatus() {
		final List<String> villainStatusList = villainController.initializeVillainStatus();
		
		assertNotNull(villainStatusList);
		assertEquals(new Integer(3),Integer.valueOf(villainStatusList.size()));
	}
	
	@Test
	public final void shouldBeDetainNoFound() {
		when(villainService.findByPK(anyLong())).thenReturn(null);
		
		final ModelAndView modelAndView = villainController.detain(VillainConstant.TEST_ID, request, redirectAttributes);
		final String message = (String) modelAndView.getModel().get(ParameterConstant.PARAM_EXCEPTION);
		
		assertEquals(ViewConstant.ERROR_VIEW,modelAndView.getViewName());
		assertEquals(MESSAGE_SOURCE_VALUE,message);
	}
	
	@Test
	public final void shouldBeDetainWithError() throws BatDeskException {
		doThrow(new BatDeskException(MESSAGE_SOURCE_VALUE)).when(villainOperationService).detain(anyLong());
		
		final ModelAndView modelAndView = villainController.detain(VillainConstant.TEST_ID, request, redirectAttributes);
	
		assertEquals(ViewConstant.REDIRECT_LIST_REQUEST_MAPPING,modelAndView.getViewName());
	}
	
	@Test
	public final void shouldBeDetain() throws BatDeskException {
		final ModelAndView modelAndView = villainController.detain(VillainConstant.TEST_ID, request, redirectAttributes);
	
		assertEquals(ViewConstant.REDIRECT_LIST_REQUEST_MAPPING,modelAndView.getViewName());
	}
	
}
