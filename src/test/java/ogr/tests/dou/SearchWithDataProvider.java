package ogr.tests.dou;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class SearchWithDataProvider {
    private final String inputXpath = ".//form[@class = 'search']//input";
    private final String searchResult = ".//div[@class = 'gs-title']//a";
    private WebDriver driver;
    private void initDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1600,1000");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("pageLoadStrategy", "slow");
        driver = new ChromeDriver(options);
    }

    @BeforeTest
    public void initTests(){
        initDriver();
    }

    @AfterTest
    public void closeDriver(){
        driver.quit();
    }

    @BeforeMethod
    public void goToMain(){
        driver.get("https://dou.ua/");
    }


    @DataProvider
    public Object[] companyName() {
        return new Object[] {
                "sigma",
                "globallogic"
        };
    }


    @Test(dataProvider = "companyName")
    public void findCompanyByNameFromDataProvider(String name){
        WebElement input = driver.findElement(By.xpath(inputXpath));
        input.sendKeys(name);
        input.sendKeys(Keys.ENTER);
        //Ждем инпут
        driver.findElement(By.xpath(".//td[@class = 'gsc-input']")).isDisplayed();
        List<WebElement> results = driver.findElements(By.xpath(searchResult));
        assertTrue(results.get(0).getText().toLowerCase().contains("name"), "Contains " + name );
    }
}
