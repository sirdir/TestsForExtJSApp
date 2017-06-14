package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftTree extends BasePage{

    public LeftTree(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".x-grid-tree-node-leaf")
    List<WebElement> letters;

    @FindBy(id = "ext-gen1061")
    private WebElement header;

    @FindBy(id = "menuitem-1013")
    private WebElement btnAdd;

    public String[] getAllLetters() {
        String arr[] = new String [letters.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = letters.get(i).getText();
        }
        return arr;
    }

    public String getHeaderText() {
        return header.getText();
    }

    public boolean isAddDisabled() {
        new Actions(driver)
                .contextClick(header)
                .perform();
        List<String> classes = Arrays.asList(btnAdd.getAttribute("class").split(" "));
        return classes.contains("x-menu-item-disabled");
    }
}