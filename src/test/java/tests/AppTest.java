package tests;

import org.apache.commons.lang3.ArrayUtils;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppTest extends BaseTest{

    private AppPage appPage;

    @BeforeClass
    public void basicSetup(){
        driver.get(url);
        appPage = PageFactory.initElements(driver, AppPage.class);
    }

    @BeforeMethod
    public void cleanUp(){
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void treeAscendingSort(){
        String expLetters[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEqualsNoOrder(appPage.leftTree.getAllLetters().toArray(), expLetters, "wrong letters in tree");
        softAssert.assertEquals(appPage.leftTree.getAllLetters().toArray(), expLetters, "wrong order of letters in tree");
        softAssert.assertAll();
    }

    @Test
    public void gridDescendingSort(){
        String expLetters[] = {"Z", "Y", "X", "W", "V", "U", "T", "S", "R", "Q", "P", "O", "N", "M", "L", "K"};
        AppPage appPage = PageFactory.initElements(driver, AppPage.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEqualsNoOrder(appPage.rightGrid.getAllLetters().toArray(), expLetters, "wrong letters in grid");
        softAssert.assertEquals(appPage.rightGrid.getAllLetters().toArray(), expLetters, "wrong order of letters in grid");
        softAssert.assertAll();
    }

    @Test
    public void namesOfHeader(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(appPage.leftTree.getHeaderText(), "English alphabet");
        softAssert.assertEquals(appPage.rightGrid.getHeaderText(), "English letter");
        softAssert.assertAll();
    }

    @Test
    public void addButtonDisabled(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(appPage.leftTree.isAddDisabled(), "btn ADD isn't disabled for tree");
        softAssert.assertTrue(appPage.rightGrid.isAddDisabled(), "btn ADD isn't disabled for grid");
        softAssert.assertAll();
    }

    @Test
    public void dropDownFromTreeToGrid(){//todo params
        String expLetters[] = {"Z", "Y", "X", "W", "V", "U", "T", "S", "R", "Q", "P", "O", "N", "M", "L", "K", "B"};
        ArrayUtils.reverse(expLetters);
        String letterB = "B";
        appPage.leftTree.moveLetterToGrid(letterB);
        Assert.assertEquals(appPage.rightGrid.getAllLetters().toArray(), expLetters);
    }

    @Test
    public void dropDownFromGridToTree(){//todo params
        String expLetters[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "U"};
        String letterU = "U";
        appPage.rightGrid.moveLetterToTree(letterU);
        Assert.assertEquals(appPage.leftTree.getAllLetters().toArray(), expLetters);
    }

    @Test
    public void multipleDropDownFromGridToTree(){ //todo params
        String expLetters[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "U", "Z"};
        appPage.rightGrid.moveMultipleLettersToTree("K", "U", "Z");
        Assert.assertEquals(appPage.leftTree.getAllLetters().toArray(), expLetters);
    }

    @Test
    public void deleteTree(){//todo params
        driver.get(url);
        String letterA = "A";
        List before = appPage.leftTree.getAllLetters();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(before.contains(letterA), "tree doesn't contains '" + letterA + "' ;");
        appPage.leftTree.deleteLetter(letterA);
        softAssert.assertFalse(before.contains(letterA), "letter '" + letterA + "' wasn't deleted from tree;");
        List after = appPage.leftTree.getAllLetters();
        softAssert.assertTrue(after.contains(letterA));
    }

    @Test(dependsOnMethods = "deleteTree")
    public void addLetterToTree(){//todo params
        String letterA = "A";
        List before = appPage.leftTree.getAllLetters();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(before.contains(letterA), "tree doesn't contains '" + letterA + "' ;");
        appPage.leftTree.deleteLetter(letterA);
        before = appPage.leftTree.getAllLetters();
        softAssert.assertFalse(before.contains(letterA), "letter '" + letterA + "' wasn't deleted from tree;");
        appPage.leftTree.addLetter(letterA);
        List after = appPage.leftTree.getAllLetters();
        softAssert.assertTrue(after.contains(letterA), "letter '" + letterA + "' wasn't added to tree;");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "deleteTree")
    public void addLetterToTreeBug(){//todo params
        String letterA = "A";
        String letterB = "B";
        List before = appPage.leftTree.getAllLetters();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(before.contains(letterA), "tree doesn't contains '" + letterA + "' ;");
        softAssert.assertTrue(before.contains(letterB), "tree doesn't contains '" + letterB + "' ;");
        appPage.leftTree.deleteLetter(letterA);
        appPage.leftTree.deleteLetter(letterB);
        before = appPage.leftTree.getAllLetters();
        softAssert.assertFalse(before.contains(letterA), "letter '" + letterA + "' wasn't deleted from tree;");
        softAssert.assertFalse(before.contains(letterB), "letter '" + letterB + "' wasn't deleted from tree;");
        appPage.leftTree.addLetterSelectBug(letterA);
        List after = appPage.leftTree.getAllLetters();
        softAssert.assertTrue(after.contains(letterA), "letter '" + letterA + "' wasn't added to tree;");
        softAssert.assertFalse(appPage.leftTree.isAddDisabled(), "button ADD id disable instead of enable;");
        softAssert.assertAll();
    }

    @Test
    public void deleteGrid(){//todo params
        String letterZ = "Z";
        List before = appPage.rightGrid.getAllLetters();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(before.contains(letterZ), "tree doesn't contains '" + letterZ + "' ;");
        appPage.rightGrid.deleteLetter(letterZ);
        before = appPage.rightGrid.getAllLetters();
        softAssert.assertFalse(before.contains(letterZ), "letter '" + letterZ + "' wasn't deleted from grid;");
    }

    @Test
    public void multiDeleteGrid(){//todo params
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
    public void addLetterToGrid(){//todo params
        String letterZ = "Z";
        List before = appPage.rightGrid.getAllLetters();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(before.contains(letterZ), "tree doesn't contains '" + letterZ + "' ;");
        appPage.rightGrid.deleteLetter(letterZ);
        before = appPage.rightGrid.getAllLetters();
        softAssert.assertFalse(before.contains(letterZ), "letter '" + letterZ + "' wasn't deleted from grid;");
        appPage.rightGrid.addLetter(letterZ);
        List after = appPage.rightGrid.getAllLetters();
        softAssert.assertTrue(after.contains(letterZ), "letter '" + letterZ + "' wasn't added to grid;");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "deleteGrid")
    public void addLetterToGridBug(){//todo params
        String letterO = "O";
        String letterZ = "Z";
        List before = appPage.rightGrid.getAllLetters();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(before.contains(letterO), "tree doesn't contains '" + letterO + "' ;");
        softAssert.assertTrue(before.contains(letterZ), "tree doesn't contains '" + letterZ + "' ;");
        appPage.rightGrid.deleteLetter(letterO);
        appPage.rightGrid.deleteLetter(letterZ);
        before = appPage.rightGrid.getAllLetters();
        softAssert.assertFalse(before.contains(letterO), "letter '" + letterO + "' wasn't deleted from grid;");
        softAssert.assertFalse(before.contains(letterZ), "letter '" + letterZ + "' wasn't deleted from grid;");
        appPage.rightGrid.addLetterSelectBug(letterO);
        List after = appPage.rightGrid.getAllLetters();
        softAssert.assertTrue(after.contains(letterO), "letter '" + letterO + "' wasn't added to gird;");
        softAssert.assertFalse(appPage.rightGrid.isAddDisabled(), "button ADD id disable instead of enable;");
        softAssert.assertAll();
    }

    @Test
    public void addWindowValidation(){//todo params
        //??
    }


}