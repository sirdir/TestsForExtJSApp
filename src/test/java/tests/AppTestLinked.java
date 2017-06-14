package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AppPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AppTestLinked extends BaseTest{

    private AppPage appPage;

    @BeforeClass
    public void bc(){
        driver.get(url);
        appPage = PageFactory.initElements(driver, AppPage.class);
    }

    @Test
    public void deleteTree(){
        String letterA = "A";
        String letterB = "B";
        String before[] = appPage.leftTree.getAllLetters();
        appPage.leftTree.deleteLetter(letterA);
        appPage.leftTree.deleteLetter(letterB);
        String after[] = appPage.leftTree.getAllLetters();
        Assert.assertTrue(Arrays.asList(after).contains(letterA));
        Assert.assertTrue(Arrays.asList(after).contains(letterB));
        Assert.assertEquals(after.length + 2, before.length);
    }

    @Test(dependsOnMethods = "deleteTree")
    public void addTreeByTyping(){
        String letter = "A";
        String before[] = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteLetter(letter);
        String after[] = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(Arrays.asList(after).contains(letter));
        Assert.assertEquals(before.length + 1, after.length);
    }

    @Test(dependsOnMethods = "deleteTree")
    public void addTreeBySelecting(){
        String letter = "B";
        String before[] = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteLetter(letter);
        String after[] = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(Arrays.asList(after).contains(letter));
        Assert.assertEquals(before.length + 1, after.length);
    }

    @Test
    public void deleteGrid(){
        String letter = "O";
        String before[] = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteLetter(letter);
        String after[] = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(Arrays.asList(after).contains(letter));
        Assert.assertEquals(after.length + 1, before.length);
    }

    @Test
    public void multyDeleteGrid(){
        String letter = "O";
        String before[] = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteLetter(letter);
        String after[] = appPage.rightGrid.getAllLetters();
    }

    @Test(dependsOnMethods = "deleteGrid")
    public void addGridByTyping(){
        String letter = "O";
        String before[] = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteLetter(letter);
        String after[] = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(Arrays.asList(after).contains(letter));
        Assert.assertEquals(before.length + 1, after.length);
    }

    @Test(dependsOnMethods = "deleteGrid")
    public void addGridBySelecting(){
        String letter = "O";
        String before[] = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteLetter(letter);
        String after[] = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(Arrays.asList(after).contains(letter));
        Assert.assertEquals(before.length + 1, after.length);
    }

    @Test
    public void addWindowValidation(){

    }

}