package com.qaproject.demo.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
public class LoginUITest {
private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterEach
    void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (driver != null) {
            driver.quit();
        }}
    @Test
    public void testLogin() {
        driver.get("http://localhost:3000");
try {
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.name("username")).sendKeys("testuser");
            driver.findElement(By.name("password")).sendKeys("password123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
        } catch (Exception e) {
            
        }
    }
}