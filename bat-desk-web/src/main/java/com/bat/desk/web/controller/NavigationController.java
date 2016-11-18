package com.bat.desk.web.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bat.desk.web.constant.ParameterConstant;
import com.bat.desk.web.constant.ViewConstant;

@Controller ("navigationController")
public class NavigationController extends CommonController {

	private static final long serialVersionUID = -2225435262148716277L;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public final String goToIndexView(final Model model) {
		model.addAttribute(ParameterConstant.PARAM_CONNECTION_TIME, new Date());
		return ViewConstant.HOME_VIEW;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String goToHomeView(final Model model) {
    	model.addAttribute(ParameterConstant.PARAM_CONNECTION_TIME, new Date());
        return ViewConstant.HOME_VIEW;
    }
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public final String goToErrorView() {
		return ViewConstant.ERROR_VIEW;
	}
	
	@RequestMapping(value = "/login")
    public String goToLoginView(final Model model) {
		return ViewConstant.LOGIN_VIEW;
    }
	
}
