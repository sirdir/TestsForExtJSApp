package tests;

import factory.Browser;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

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

    @DataProvider
    public static Object[][] treeToGrid() {
        return new Object[][]{
                {new String[] {"B", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}, "B"},

        };
    }

    @DataProvider
    public static Object[][] gridToTree() {
        return new Object[][]{
                {new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "U"}, "U"},
        };
    }

    @DataProvider
    public static Object[][] multiGridToTree() {
        return new Object[][]{
                {new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "U", "Z"}, new String[] {"K", "U", "Z"}},
        };
    }

    @DataProvider
    public static Object[][] treeLetters() {
        return new Object[][]{
                {"A", "B"}
        };
    }

    @DataProvider
    public static Object[][] gridLetters() {
        return new Object[][]{
                {"Z", "U", "K"}
        };
    }


}
