package pages.pageblocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

/**
 * Created by DiR on 15.06.2017.
 */
public class Popup extends BasePage {
    public Popup(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "combo-1029-inputEl")
    private WebElement input;

    @FindBy(css = ".x-boundlist-list-ct>ul>li")
    private List<WebElement> listLettersToAdd;

    @FindBy(id = "ext-gen1127")
    private WebElement ddAdd;

    @FindBy(id = "button-1031")
    private WebElement btnAdd;


    public void addLetter(String letter) {
        ddAdd.click();
        listLettersToAdd.forEach(el -> {
            if (el.getText().equals(letter)){
                el.click();
            }
        });
        btnAdd.click();
    }

    public void addLetterBug(String letter) {
        input.sendKeys(letter);
        listLettersToAdd.forEach(el -> {
            if (el.getText().equals(letter)){
                el.click();
            }
        });
        input.sendKeys(" ");
        btnAdd.click();
    }
}
