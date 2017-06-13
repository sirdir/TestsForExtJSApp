package factory;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
    public static WebDriver getBrowser(Browser browserName) {
        switch (browserName){
            default:
            case CHROME:
                return initChrome();
            case FIREFOX:
                return initFirefox();
            case EDGE:
                return initEdge();
            case IE:
                return initIe();
        }
    }

    private static ChromeDriver initChrome() {
        ChromeDriverManager.getInstance().setup();
        return new ChromeDriver();
    }

    private static FirefoxDriver initFirefox() {
        FirefoxDriverManager.getInstance().setup();
        return new FirefoxDriver();
    }

    private static EdgeDriver initEdge() {
        assert System.getProperty("os.name").equals("Linux");
        EdgeDriverManager.getInstance().setup();
        return new EdgeDriver();
    }

    private static InternetExplorerDriver initIe() {
        assert System.getProperty("os.name").equals("Linux");
        InternetExplorerDriverManager.getInstance().setup();
        return new InternetExplorerDriver();
    }
}