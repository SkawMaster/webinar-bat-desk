package com.bat.desk.web.selenium;


import com.bat.SpringBootWebApplication;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootWebApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class HomeSelenium extends FluentTest {

    @Value("${local.server.port:8080}")
    private int serverPort;

    @Value("${application.endpoint.url:http://localhost}")
    private String applicationEndpoint;

    @Value("${server.contextPath:/bat-desk}")
    private String applicationContextPath;

    private WebDriver webDriver = new PhantomJSDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    private String getUrl() {
        return applicationEndpoint + ":" + serverPort + applicationContextPath;
    }


    @Test
    public void hasPageTitle() {
        goTo(getUrl());

        assertEquals("Bat-Desk", find("#titleHeader").getText());
    }

}