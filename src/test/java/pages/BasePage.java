package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    final WebDriver driver;

    BasePage(WebDriver driver){
        this.driver = driver;
    }
}