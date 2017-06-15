package tests;

import factory.Browser;
import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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


    void setPageSize(Dimension dimension) {
        long before = (long) ((JavascriptExecutor)driver).executeScript("return window.innerWidth");
        driver.manage().window().setSize(dimension);
        new WebDriverWait(driver, 10)
                .until((ExpectedCondition<Boolean>) input ->
                        input.manage().window().getSize().getWidth() == dimension.getWidth()
                );
        new WebDriverWait(driver, 10)
                .until((ExpectedCondition<Boolean>) input ->
                        before != (long) ((JavascriptExecutor)input).executeScript("return window.innerWidth")
                );
        new WebDriverWait(driver, 10).until(                new ExpectedCondition<Boolean>() {
                    private long after;

                    @Override
                    public Boolean apply(WebDriver input) {
                        after = (long) ((JavascriptExecutor)input).executeScript("return window.innerWidth");
                        return after != before;
                    }

                    @Override
                    public String toString() {
                        return String.format("title to contain \"%d\". Current title: \"%d\"", after, before);
                    }
                });

    }
}
