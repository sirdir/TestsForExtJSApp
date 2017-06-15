package pages;

import org.openqa.selenium.By;
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

    @FindBy(xpath = "//tr[contains(@id, 'treeview-1017-record-ext-record')]")
    List<WebElement> letters;

    @FindBy(id = "ext-gen1061")
    private WebElement header;

    @FindBy(id = "menuitem-1013")
    private WebElement btnAdd;

    @FindBy(id = "menuitem-1014")
    private WebElement btnDelete;

    public List getAllLetters() {
        ArrayList<String> arr = new ArrayList<>(letters.size());
        letters.forEach(el -> arr.add(el.getText()));
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

    public void deleteLetter(String letter) {
        letters.forEach(el -> {
            if (el.getText().equals(letter)){
                new Actions(driver)
                        .contextClick(el)
                        .perform();
                btnDelete.click();
            }
        });
    }

    public void moveLetterToGrid(String letter) {
        letters.forEach(el -> {
            if (el.getText().equals(letter)){
                new Actions(driver)
                        .dragAndDrop(el, driver.findElement(By.id("dd-grid-1018-body")))
                        .perform();
            }
        });
    }
}