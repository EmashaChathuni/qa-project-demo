package com.qaproject.demo.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class AddRecipeUITest {
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
    public void testAddRecipe() {
        driver.get("http://localhost:3000");

        try {
            driver.findElement(By.linkText("Recipes")).click();
            driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
            driver.findElement(By.name("title")).sendKeys("Pasta Carbonara");
            driver.findElement(By.name("description")).sendKeys("Classic Italian pasta");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
        } catch (Exception e) {
            
        }
    }
}