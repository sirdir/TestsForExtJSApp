package tests;

import factory.Browser;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void setup(){
        driver = DriverFactory.getBrowser(Browser.CHROME);
    }

    @AfterSuite
    public void teardown(){
        driver.quit();
    }
}
