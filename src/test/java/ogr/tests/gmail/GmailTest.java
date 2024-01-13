package ogr.tests.gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class GmailTest {
    private final String inputXpath = ".//input[@type = 'email']";
    WebDriver driver;
    private void initDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1600,1000");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("pageLoadStrategy", "slow");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeSuite
    public void initTests(){
        initDriver();
    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();
    }

    @BeforeMethod
    public void goToBase(){
        driver.get("https://www.google.com/intl/ru/gmail/about/");
        driver.findElement(By.xpath(".//a[@data-action = 'sign in']")).click();
    }

    @Test
    public void test1(){
        Assert.assertTrue(driver.findElement(By.xpath(inputXpath)).isDisplayed());
    }

    @Test
    public void test2(){
        assertEquals(driver.findElement(By.xpath(inputXpath)).getAttribute("aria-label"), "Телефон или адрес эл. почты");
    }

    @Test
    public void test3() throws InterruptedException {
        driver.findElement(By.xpath(inputXpath)).sendKeys("TestEmail");
        driver.findElement(By.xpath(".//div[@id='identifierNext']//button")).click();
        //Ждем кнопку на другой странице
        driver.findElement(By.xpath(".//a[@aria-label = 'Повторить попытку']")).isDisplayed();
        //Ждем полной загрузки, не подтягивает хедер
        Thread.sleep(500);
        assertEquals(driver.findElement(By.xpath(".//h1[@id='headingText']")).getText(), "Не удалось войти в аккаунт");
    }

    @Test
    public void testWithAction() throws InterruptedException {
        driver.findElement(By.xpath(inputXpath)).sendKeys("TestEmail");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(".//span[text() = 'Далее']/.."))).click().perform();
        //Ждем кнопку на другой странице
        driver.findElement(By.xpath(".//a[@aria-label = 'Повторить попытку']")).isDisplayed();
        //Ждем полной загрузки, не подтягивает хедер
        Thread.sleep(500);
        assertEquals(driver.findElement(By.xpath(".//h1[@id='headingText']")).getText(), "Не удалось войти в аккаунт");
    }
}
