package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RightGrid extends BasePage{
    RightGrid(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#gridview-1021-body>.x-grid-row")
    List<WebElement> letters;

    @FindBy(css = "#button-1023")
    WebElement btnAdd;

    @FindBy(css = "#button-1024")
    WebElement btnDelete;
}