package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.pageblocks.LeftTree;
import pages.pageblocks.RightGrid;

import javax.swing.*;

public class AppPage extends BasePage{

    public AppPage(WebDriver driver) {
        super(driver);
        leftTree = PageFactory.initElements(driver, LeftTree.class);
        rightGrid = PageFactory.initElements(driver, RightGrid.class);
        wait.until(ExpectedConditions.titleIs("DD"));
    }

    public LeftTree leftTree;
    public RightGrid rightGrid;

    @FindBy(id = "dd-tree-1011")
    private WebElement tree;

    @FindBy(id = "dd-grid-1018")
    private WebElement grid;

    @FindBy(id = "app-main-1010")
    private WebElement page;

    public int getTreeWidth() {
        return getIntValue(tree);
    }

    public int getGridWidth() {
        return getIntValue(grid);
    }

    public int getPageWidth() {
        return getIntValue(page);
    }

    private int getIntValue(WebElement el){
        String width = el.getCssValue("width");
        width = width.substring(0, width.length()-2);
        return Integer.valueOf(width);
    }
}