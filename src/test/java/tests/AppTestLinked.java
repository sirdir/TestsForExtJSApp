package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AppPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        List before = appPage.leftTree.getAllLetters();
        appPage.leftTree.deleteLetter(letterA);
        appPage.leftTree.deleteLetter(letterB);
        List after = appPage.leftTree.getAllLetters();
        Assert.assertTrue(after.contains(letterA));
        Assert.assertTrue(after.contains(letterB));
        Assert.assertEquals(after.size() + 2, before.size());
    }

    @Test(dependsOnMethods = "deleteTree")
    public void addTreeByTyping(){
        String letter = "A";
        List before = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteLetter(letter);
        List after = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(after.contains(letter));
        Assert.assertEquals(before.size() + 1, after.size());
    }

    @Test(dependsOnMethods = "deleteTree")
    public void addTreeBySelecting(){
        String letter = "B";
        List before= appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteLetter(letter);
        List after = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(after.contains(letter));
        Assert.assertEquals(before.size() + 1, after.size());
    }

    @Test
    public void deleteGrid(){
        driver.get(url);
        String letter1 = "O";
        String letter2 = "Z";
        List before = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteLetter(letter1);
        appPage.rightGrid.deleteLetter(letter2);
        List after = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(after.contains(letter1));
        Assert.assertTrue(after.contains(letter2));
        Assert.assertEquals(after.size() + 2, before.size());
    }

    @Test
    public void multiDeleteGrid(){
        driver.get(url);
        String arr[] = {"K", "U", "Z"};
        List<String> toDel = Arrays.asList(arr);
        List before = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteMultipleLetters(toDel);
        List after = appPage.rightGrid.getAllLetters();
        Assert.assertEquals(after.size() + 3, before.size());
        Assert.assertFalse(after.contains(toDel.get(0)));
        Assert.assertFalse(after.contains(toDel.get(1)));
        Assert.assertFalse(after.contains(toDel.get(2)));
        after.addAll(toDel);
        Collections.sort(after);
        Assert.assertEquals(after, before);
    }

    @Test(dependsOnMethods = "deleteGrid")
    public void addGridByTyping(){
        String letter = "O";
        List before = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.addLetterBySelect(letter);
        List after = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(after.contains(letter));
        Assert.assertEquals(before.size() + 1, after.size());
    }

    @Test(dependsOnMethods = "deleteGrid")
    public void addGridBySelecting(){
        String letter = "Z";
        List before = appPage.rightGrid.getAllLetters();
        appPage.rightGrid.deleteLetter(letter);
        List after = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(after.contains(letter));
        Assert.assertEquals(before.size() + 1, after.size());
    }

    @Test
    public void addWindowValidation(){
        //??
    }

}