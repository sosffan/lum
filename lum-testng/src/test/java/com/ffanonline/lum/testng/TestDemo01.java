package com.ffanonline.lum.testng;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import static org.testng.Assert.assertEquals;

/**
 * Created by fanfei on 11/06/2018.
 */

@Jira("annotation on the class")
public class TestDemo01 {
    @Test
    @Tag(name = "tag01")
    @Jira("jira-01")
    public void test01() {

    }

    @Test
    @Jira("jira-02, jira-03")
    @Tag(name = "test tag02")
    public void test02() {
        assertEquals("", "a");
    }

    @Test(enabled = false)
    public void test03() {
        System.setProperty("webdriver.gecko.driver","/Users/fanfei/workspace/tools/geckodriver");
        System.setProperty("name", "testname");
        System.out.println(System.getProperty("name"));
        System.out.println(System.getProperty("webdriver.gecko.driver"));
        System.out.println("//////");

        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.baidu.com");


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(driver.getTitle(), "baidu");
    }

    @AfterTest
    public void test() {
    }

}
