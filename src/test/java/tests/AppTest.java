package tests;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AppPage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppTest extends BaseTest{

    private AppPage appPage;

    @DataProvider
    public static Object[][] treeToGrid() {
        return new Object[][]{
                {new String[] {"B", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}, "B"},

        };
    }

    @DataProvider
    public static Object[][] gridToTree() {
        return new Object[][]{
                {new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "U"}, "U"},
        };
    }

    @DataProvider
    public static Object[][] multiGridToTree() {
        return new Object[][]{
                {new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "U", "Z"}, new String[] {"K", "U", "Z"}},
        };
    }

    @DataProvider
    public static Object[][] treeLetters() {
        return new Object[][]{
                {"A", "B"}
        };
    }

    @DataProvider
    public static Object[][] gridLetters() {
        return new Object[][]{
                {"Z", "U", "K"}
        };
    }

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

    @Test(dataProvider = "treeToGrid")
    public void dropDownFromTreeToGrid(String expLetters[], String letter){
        appPage.leftTree.moveLetterToGrid(letter);
        Assert.assertEquals(appPage.rightGrid.getAllLetters().toArray(), expLetters);
    }

    @Test(dataProvider = "gridToTree")
    public void dropDownFromGridToTree(String expLetters[], String letter){
        appPage.rightGrid.moveLetterToTree(letter);
        Assert.assertEquals(appPage.leftTree.getAllLetters().toArray(), expLetters);
    }

    @Test(dataProvider = "multiGridToTree")
    public void multipleDropDownFromGridToTree(String expLetters[], String toMove[]){
        appPage.rightGrid.moveMultipleLettersToTree(toMove);
        Assert.assertEquals(appPage.leftTree.getAllLetters().toArray(), expLetters);
    }

    @Test(dataProvider = "treeLetters")
    public void deleteTree(String letter1, String letter2){
        driver.get(url);
        List before = appPage.leftTree.getAllLetters();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(before.contains(letter1), "tree doesn't contains '" + letter1 + "' ;");
        appPage.leftTree.deleteLetter(letter1);
        softAssert.assertFalse(before.contains(letter1), "letter '" + letter1 + "' wasn't deleted from tree;");
        List after = appPage.leftTree.getAllLetters();
        softAssert.assertTrue(after.contains(letter1));
    }

    @Test(dependsOnMethods = "deleteTree", dataProvider = "treeLetters")
    public void addLetterToTree(String letter1, String letter2){
        appPage.leftTree.deleteLetter(letter1);
        appPage.leftTree.addLetter(letter1);
        List after = appPage.leftTree.getAllLetters();
        Assert.assertTrue(after.contains(letter1), "letter '" + letter1 + "' wasn't added to tree;");   }

    @Test(dependsOnMethods = "deleteTree", dataProvider = "treeLetters")
    public void addLetterToTreeBug(String letter1, String letter2){
        appPage.leftTree.deleteLetter(letter1);
        appPage.leftTree.deleteLetter(letter2);
        appPage.leftTree.addLetterSelectBug(letter1);
        List after = appPage.leftTree.getAllLetters();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(after.contains(letter1), "letter '" + letter1 + "' wasn't added to tree;");
        softAssert.assertFalse(appPage.leftTree.isAddDisabled(), "button ADD id disable instead of enable;");
        softAssert.assertAll();
    }

    @Test(dataProvider = "gridLetters")
    public void deleteGrid(String letter1, String letter2, String letter3){
        List before = appPage.rightGrid.getAllLetters();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(before.contains(letter1), "tree doesn't contains '" + letter1 + "' ;");
        appPage.rightGrid.deleteLetter(letter1);
        before = appPage.rightGrid.getAllLetters();
        softAssert.assertFalse(before.contains(letter1), "letter '" + letter1 + "' wasn't deleted from grid;");
    }

    @Test(dataProvider = "gridLetters")
    public void multiDeleteGrid(String... arr){
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

    @Test(dependsOnMethods = "deleteGrid", dataProvider = "gridLetters")
    public void addLetterToGrid(String letter1, String letter2, String letter3){
        appPage.rightGrid.deleteLetter(letter1);
        appPage.rightGrid.addLetter(letter1);
        List after = appPage.rightGrid.getAllLetters();
        Assert.assertTrue(after.contains(letter1), "letter '" + letter1 + "' wasn't added to grid;");
    }

    @Test(dependsOnMethods = "deleteGrid", dataProvider = "gridLetters")
    public void addLetterToGridBug(String letter1, String letter2, String letter3){
        appPage.rightGrid.deleteLetter(letter2);
        appPage.rightGrid.deleteLetter(letter1);
        appPage.rightGrid.addLetterSelectBug(letter2);
        List after = appPage.rightGrid.getAllLetters();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(after.contains(letter2), "letter '" + letter2 + "' wasn't added to gird;");
        softAssert.assertFalse(appPage.rightGrid.isAddDisabled(), "button ADD id disable instead of enable;");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "deleteGrid", dataProvider = "gridLetters")
    public void addWindowValidation(String letter1, String letter2, String letter3){
        String invalidLetter = letter1 + letter2 + letter3
        appPage.rightGrid.deleteLetter(letter1);
        appPage.rightGrid.addLetter(invalidLetter);
        Assert.assertTrue(appPage.popup.isPopupValidationInvalid(), "input field in popup don't have any highlighting");
    }


}