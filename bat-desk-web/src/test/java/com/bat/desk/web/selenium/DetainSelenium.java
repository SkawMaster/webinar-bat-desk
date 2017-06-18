package com.bat.desk.web.selenium;


import com.bat.SpringBootWebApplication;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootWebApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class DetainSelenium extends FluentTest {

    private final String NAVIGATION_VILLAIN_LIST = "navigation_villain_list";

    @Value("${server.port:8080}")
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
    public void testBatDeskPage_Dead() {
        goTo(getUrl());
        // Villain List
        find(By.id(NAVIGATION_VILLAIN_LIST)).click();

        // Detain Dead Villain
        find(By.id("detain_4")).click();
        find(By.id("notificationException"));
    }

    @Test
    public void testBatDeskPage_Detain() {
        goTo(getUrl());
        // Villain List
        find(By.id(NAVIGATION_VILLAIN_LIST)).click();

        // Detain Detain Villain
        find(By.id("detain_2")).click();
        find(By.id("notificationException"));
    }

    @Test
    public void testBatDeskPage_Free() {
        goTo(getUrl());
        // Villain List
        find(By.id(NAVIGATION_VILLAIN_LIST)).click();

        // Detain Free Villain
        find(By.id("detain_1")).click();
        find(By.id("notificationMessage"));
    }

}