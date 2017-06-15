package com.bat.desk.web.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.context.ThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={WebConfig.class})
public class WebConfigTestIT {

    @Autowired
	private ViewResolver viewResolver;
	
	@Autowired
	private FilterRegistrationBean filterRegistrationBean;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;

	@Autowired
	private ThemeSource themeSource;
	
	@Autowired
	private ThemeResolver themeResolver;
	
	@Autowired
	private ThemeChangeInterceptor themeChangeInterceptor;
	
	@Test
	public void shouldBeInjected() {
		assertNotNull(viewResolver);
		assertNotNull(filterRegistrationBean);
		assertNotNull(messageSource);
		assertNotNull(localeResolver);
		assertNotNull(localeChangeInterceptor);
		assertNotNull(themeSource);
		assertNotNull(themeResolver);
		assertNotNull(themeChangeInterceptor);
	}

}
