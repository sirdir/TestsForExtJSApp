package tests;

import factory.Browser;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;
    String url;

    @BeforeClass(alwaysRun = true)
    public void setup(){
        driver = DriverFactory.getBrowser(Browser.CHROME);
        String[] relative = {"build", "resources", "test", "Aai", "index.html"};
        Path path = Paths.get(new File("").getAbsolutePath(), relative);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        url = path.toUri().toString();
        System.out.println(url);
    }

    @AfterClass(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }
}
