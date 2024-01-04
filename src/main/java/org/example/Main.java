package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://dou.ua/");
        WebElement input = driver.findElement(By.xpath(".//input[@id = 'txtGlobalSearch']"));
        input.sendKeys("Alevel");
        input.sendKeys(Keys.ENTER);
        driver.quit();
    }
}