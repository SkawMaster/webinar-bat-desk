package com.bat.desk.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.bat.desk.common.exception.BatDeskException;
import com.bat.desk.web.constant.ParameterConstant;
import com.bat.desk.web.constant.ViewConstant;
import com.bat.villain.core.entity.Villain;
import com.bat.villain.core.enumerate.VillainStatusEnum;
import com.bat.villain.core.service.VillainOperationService;
import com.bat.villain.core.service.VillainService;
import com.bat.villain.core.validation.VillainValidation;

@Controller("villainController")
@RequestMapping(value = "/villain/*")
public class VillainController {

	private final Logger LOG = LoggerFactory.getLogger(VillainController.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private VillainService villainService;

	@Autowired
	private VillainOperationService villainOperationService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public final String list(final Model model) {
		LOG.info("List all villains");
		final List<Villain> villainList = villainService.findAll();
		model.addAttribute(ParameterConstant.PARAM_LIST, villainList);
		return ViewConstant.VILLAIN_FOLDER + ViewConstant.VILLAIN_LIST_VIEW;
	}

	@RequestMapping(value = "/detain", method = RequestMethod.GET)
	public ModelAndView detain(@RequestParam("id") Long villainId, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		LOG.info("Detaining Villain with id : {}", villainId);
		ModelAndView modelAndView = new ModelAndView(ViewConstant.REDIRECT_LIST_REQUEST_MAPPING);
		
		Villain villainFound = villainService.findByPK(villainId);
		
		if (!VillainValidation.INSTANCE.isValid(villainFound)) {
			final String error = messageSource.getMessage("villain.validation.error.NOT_FOUND", new Object[] { villainId }, RequestContextUtils.getLocale(request) );
			modelAndView.addObject(ParameterConstant.PARAM_EXCEPTION, error);
			modelAndView.setViewName(ViewConstant.ERROR_VIEW);
		} else {
		
			try {
				villainOperationService.detain(villainId);
				final String success = messageSource.getMessage("villain.validation.IS_DETAINED", new Object[] { villainId }, RequestContextUtils.getLocale(request) );
				redirectAttributes.addFlashAttribute(ParameterConstant.PARAM_MESSAGE, success);
				LOG.info("Villain with id {} is detained", villainId);
			} catch (BatDeskException e) {
				LOG.error("BatDeskException : Villain with id {} not detained by '{}'", villainId, e.getMessage());
				final String error = messageSource.getMessage("villain.validation.error."+e.getMessage(), new Object[] { villainId }, RequestContextUtils.getLocale(request) );
				redirectAttributes.addFlashAttribute(ParameterConstant.PARAM_EXCEPTION, error );
			}
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/showEdit", method = RequestMethod.GET)
	public final ModelAndView showEdit(@RequestParam("id") Long villainId,final Model model, HttpServletRequest request) {
		LOG.info("Show Edit Villain with id : {}", villainId);
		ModelAndView modelAndView = new ModelAndView(ViewConstant.VILLAIN_FOLDER + ViewConstant.VILLAIN_EDIT_VIEW);
		
		Villain villainShowEdit = villainService.findByPK(villainId);
		if (!VillainValidation.INSTANCE.isValid(villainShowEdit)) {
			final String error = messageSource.getMessage("villain.validation.error.NOT_FOUND", new Object[] { villainId }, RequestContextUtils.getLocale(request) );
			modelAndView.addObject(ParameterConstant.PARAM_EXCEPTION, error);
			modelAndView.setViewName(ViewConstant.ERROR_VIEW);
		} else {
			modelAndView.addObject(ParameterConstant.PARAM_VILLAIN, villainShowEdit);	
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@Valid Villain villain, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		LOG.info("Editing Villain with id : {}", villain.getId());

		villainService.update(villain);

		final String success = messageSource.getMessage("villain.validation.IS_UPDATED", new Object[] { villain.getId() }, RequestContextUtils.getLocale(request) );
		redirectAttributes.addFlashAttribute(ParameterConstant.PARAM_MESSAGE, success);
		
		LOG.info("Villain with id {} is edited", villain.getId());
		return ViewConstant.REDIRECT_LIST_REQUEST_MAPPING;
	}
	
	@RequestMapping(value = "/showAdd", method = RequestMethod.GET)
	public final String showAdd(final Model model) {
		LOG.info("Show Add Villain ");
		Villain villain = new Villain();
		model.addAttribute(ParameterConstant.PARAM_VILLAIN, villain);
		return ViewConstant.VILLAIN_FOLDER + ViewConstant.VILLAIN_ADD_VIEW;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid Villain villain, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		LOG.trace("Adding Villain with name : {}", villain.getName());

		villain.setStatus(VillainStatusEnum.FREE);
		villainService.insert(villain);

		final String success = messageSource.getMessage("villain.validation.IS_ADDED", new Object[] { villain.getName() }, RequestContextUtils.getLocale(request) );
		redirectAttributes.addFlashAttribute(ParameterConstant.PARAM_MESSAGE, success);

		LOG.info("Villain with id {} is added", villain.getId());
		return ViewConstant.REDIRECT_LIST_REQUEST_MAPPING;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") Long villainId, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		LOG.info("Deleting Villain with id : {}", villainId);
		ModelAndView modelAndView = new ModelAndView(ViewConstant.REDIRECT_LIST_REQUEST_MAPPING);
		
		Villain villainDeleted = villainService.findByPK(villainId);
	
		if (!VillainValidation.INSTANCE.isValid(villainDeleted)) {
			final String error = messageSource.getMessage("villain.validation.error.NOT_FOUND", new Object[] { villainId }, RequestContextUtils.getLocale(request) );
			modelAndView.addObject(ParameterConstant.PARAM_EXCEPTION, error);
			modelAndView.setViewName(ViewConstant.ERROR_VIEW);
		} else {
			villainService.delete(villainId);
			
			final String success = messageSource.getMessage("villain.validation.IS_DELETED", new Object[] { villainId }, RequestContextUtils.getLocale(request) );
			redirectAttributes.addFlashAttribute(ParameterConstant.PARAM_MESSAGE, success);
			LOG.info("Villain with id {} is deleted", villainId);
		}

		return modelAndView;
	}
	
	@ModelAttribute("villainStatus")
	public List<String> initializeVillainStatus() {
		List<VillainStatusEnum> villainStatusEnumList = Arrays.asList(VillainStatusEnum.values());
		List<String> result = new ArrayList<String>();
		for(VillainStatusEnum villainStatus : villainStatusEnumList){
			result.add(villainStatus.name());
		}

		return result;
	}
	
	public void setVillainService(VillainService villainService) {
		this.villainService = villainService;
	}

	public void setVillainOperationService(VillainOperationService villainOperationService) {
		this.villainOperationService = villainOperationService;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}
