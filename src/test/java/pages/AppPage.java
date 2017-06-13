package pages;

import org.openqa.selenium.WebDriver;

public class AppPage extends BasePage{

    LeftTree leftTree;
    RightGrid rightGrid;

    AppPage(WebDriver driver) {
        super(driver);
        leftTree = new LeftTree(driver);
        rightGrid = new RightGrid(driver);
    }

}