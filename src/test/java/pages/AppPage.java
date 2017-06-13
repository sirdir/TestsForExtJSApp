package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AppPage extends BasePage{

    public LeftTree leftTree;
    public RightGrid rightGrid;

    public AppPage(WebDriver driver) {
        super(driver);
        leftTree = PageFactory.initElements(driver, LeftTree.class);
        rightGrid = PageFactory.initElements(driver, RightGrid.class);
        wait.until(ExpectedConditions.titleIs("DD"));
    }

}