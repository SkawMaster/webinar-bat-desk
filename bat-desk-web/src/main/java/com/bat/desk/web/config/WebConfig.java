package com.bat.desk.web.config;

import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.bat.desk.web.constant.WebConfigConstant;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


    private static final String MEDIA_TYPE_XML = "xml",
            MEDIA_TYPE_JSON = "json",
            HTTP_HEADER_ACCEPT_PARAMETER = "mediaType";

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.
                favorPathExtension(false).
                favorParameter(true).
                parameterName(HTTP_HEADER_ACCEPT_PARAMETER).
                ignoreAcceptHeader(true).
                useJaf(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType(MEDIA_TYPE_XML, MediaType.APPLICATION_XML).
                mediaType(MEDIA_TYPE_JSON, MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        converter.setObjectMapper(objectMapper);
        converters.add(converter);

        super.configureMessageConverters(converters);
    }

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(WebConfigConstant.VIEW_RESOLVER_PREFIX);
		viewResolver.setSuffix(WebConfigConstant.VIEW_RESOLVER_SUFFIX);
		return viewResolver;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(WebConfigConstant.ENCODING);
		registrationBean.setFilter(characterEncodingFilter);
		return registrationBean;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(WebConfigConstant.MESSAGE_SOURCE_BASE_NAME);
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.getDefault(Locale.Category.FORMAT));
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName(WebConfigConstant.LOCALE_INTERCEPTOR_PARAM);
		return lci;
	}

	@Bean
	public ThemeSource themeSource() {
		ResourceBundleThemeSource source = new ResourceBundleThemeSource();
		source.setBasenamePrefix(WebConfigConstant.THEME_PREFIX);
		return source;
	}

	@Bean
	public ThemeResolver themeResolver() {
		SessionThemeResolver resolver = new SessionThemeResolver();
		resolver.setDefaultThemeName(WebConfigConstant.THEME_DEFAULT);
		return resolver;
	}

	@Bean
	public ThemeChangeInterceptor themeChangeInterceptor() {
		ThemeChangeInterceptor themeInterceptor = new ThemeChangeInterceptor();
		themeInterceptor.setParamName(WebConfigConstant.THEME_INTERCEPTOR_PARAM);
		return themeInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(themeChangeInterceptor());
	}
}
