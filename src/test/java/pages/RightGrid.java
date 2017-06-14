package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void selectByNames(String... letters){

    }

    public void addLetter(){
        Select selcet = new Select(btnAdd);
    }

    public String[] getAllLetters() {
        String arr[] = new String [lettersName.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = lettersName.get(i).getText();
        }
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
}