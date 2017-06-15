package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RightGrid extends BasePage{

    public RightGrid(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "gridcolumn-1020-textEl")
    private WebElement header;

    @FindBy(css = "#gridview-1021-body>.x-grid-row")
    private List<WebElement> lettersRow;

    @FindBy(css = "#gridview-1021-body td:nth-child(2)")
    private List<WebElement> lettersName;

    @FindBy(css = "#gridview-1021-body .x-grid-cell-inner>div")
    private List<WebElement> chbLetters;

    @FindBy(id = "button-1023")
    private WebElement btnAdd;

    @FindBy(id = "button-1024")
    private WebElement btnDelete;

    public void selectByName(String letter){
        lettersName.forEach(webElement -> {
            if (webElement.getText().equals(letter)){
                webElement.click();
            }
        });
    }
    public void selectByNames(String... letters){

    }

    public void addLetterBySelect(String letter){
        btnAdd.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-letter-1028")));
        WebElement ddAdd = driver.findElement(By.id("ext-gen1216"));
        List<WebElement> listLettersToAdd = driver.findElements(By.cssSelector("#boundlist-1035-listEl>ul>li"));

    }

    public List getAllLetters() {
        ArrayList<String> arr = new ArrayList<>(lettersName.size());
        lettersName.forEach(el -> arr.add(el.getText()));
        return arr;
    }

    public String getHeaderText() {
        return header.getText();
    }

    public boolean isAddDisabled() {
        List<String> classes = Arrays.asList(btnAdd.getAttribute("class").split(" "));
        return classes.contains("x-btn-disabled") && classes.contains("x-btn-default-toolbar-small-disabled");
    }

    public void deleteLetter(String letter) {
        lettersName.forEach(el -> {
            if (el.getText().equals(letter)){
                el.click();
                btnDelete.click();
            }
        });
    }

    public void moveLetterToTree(String letter) {
        lettersName.forEach(el -> {
            if (el.getText().equals(letter)){
                new Actions(driver)
                        .dragAndDrop(el, driver.findElement(By.id("treeview-1017-body")))
                        .perform();
            }
        });
    }

    public void moveMultipleLettersToTree(String... letters) {
        int lastRow = selectMultipleLetters(letters);
        new Actions(driver)
                .dragAndDrop(lettersRow.get(lastRow), driver.findElement(By.id("treeview-1017-body")))
                .perform();
    }

    private int selectMultipleLetters(String... letters){
        int lastRow = -1;
        for (int i = 0; i < lettersName.size(); i++) {
            if (Arrays.asList(letters).contains(lettersName.get(i).getText())){
                chbLetters.get(i).click();
                lastRow = i;
            }
        }
        return lastRow;
    }

    public void deleteMultipleLetters(String... letters) {
        int lastRow = selectMultipleLetters(letters);
        btnDelete.click();
    }

    public void deleteMultipleLetters(List<String> list) {
        int lastRow = selectMultipleLetters((String[]) list.toArray());
        btnDelete.click();
    }
}