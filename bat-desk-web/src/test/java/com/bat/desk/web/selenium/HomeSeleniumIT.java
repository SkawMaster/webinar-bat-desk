package com.bat.desk.web.selenium;


import static org.junit.Assert.assertEquals;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bat.SpringBootWebApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootWebApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class HomeSeleniumIT extends FluentTest {
	
	@Value("${local.server.port}")
	private int serverPort;
	private WebDriver webDriver = new PhantomJSDriver();
	
	@Override
	public WebDriver getDefaultDriver() {
	  return webDriver;
	}
	
	private String getUrl() {
     return "http://localhost:8080/bat-desk";
	}
		  
	@Test
	public void hasPageTitle() {
	  goTo(getUrl());
	  
	  assertEquals("Bat-Desk",find("#titleHeader").getText());
	}

}