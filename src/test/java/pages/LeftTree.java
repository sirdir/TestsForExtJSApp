package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LeftTree extends BasePage{
    public LeftTree(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".x-grid-tree-node-leaf")
    List<WebElement> letters;
}