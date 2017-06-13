package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class RightGrid extends BasePage{
    public RightGrid(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#gridview-1021-body>.x-grid-row")
    private List<WebElement> lettersRow;

    @FindBy(css = "#gridview-1021-body td:nth-child(2)")
    private List<WebElement> lettersName;

    @FindBy(css = "#gridview-1021-body .x-grid-cell-inner>div")
    private List<WebElement> chbLetters;

    @FindBy(css = "#button-1023")
    private WebElement btnAdd;

    @FindBy(css = "#button-1024")
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

}