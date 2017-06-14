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

public class AppTestSingle extends BaseTest{

    private AppPage appPage;

    @BeforeClass
    public void bc(){
        driver.get(url);
        appPage = PageFactory.initElements(driver, AppPage.class);
    }

    @BeforeMethod
    public void dosmth(){
        driver.manage().window().maximize();
        driver.get(url);
    }

    private int borders = 30;
    private Dimension smallDimension = new Dimension( 600+borders, 500);

    @Test
    public void adaptiveLayout(){
        appPage.getTreeWidth();
        appPage.getGridWidth();
        appPage.getPageWidth();
        Assert.assertEquals(appPage.getPageWidth(), driver.manage().window().getSize().getWidth());

        driver.manage().window().setSize(smallDimension);


        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfWindowsToBe(1));
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean> () {
            @Override
            public Boolean apply(WebDriver input) {
                return appPage.getPageWidth() == smallDimension.getWidth();
            }
        });
        int expTreeWidth = (int)(0.35 * (appPage.getPageWidth() - borders));
        int expGridWidth = (int)(0.65 * (appPage.getPageWidth() - borders));
        System.out.println(appPage.getPageWidth());
        Assert.assertEquals(appPage.getPageWidth(), smallDimension.getWidth());
        Assert.assertEquals(appPage.getTreeWidth(), expTreeWidth, "tree size not match 35% of width");
        Assert.assertEquals(appPage.getGridWidth(), expGridWidth, "grid size not match 65% of width");
    }

    @Test
    public void treeAscendingSort(){
        String expLetters[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEqualsNoOrder(appPage.leftTree.getAllLetters(), expLetters, "wrong letters in tree");
        softAssert.assertEquals(appPage.leftTree.getAllLetters(), expLetters, "wrong order of letters in tree");
        softAssert.assertAll();
    }

    @Test
    public void gridDescendingSort(){
        String expLetters[] = {"Z", "Y", "X", "W", "V", "U", "T", "S", "R", "Q", "P", "O", "N", "M", "L", "K"};
        AppPage appPage = PageFactory.initElements(driver, AppPage.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEqualsNoOrder(appPage.rightGrid.getAllLetters(), expLetters, "wrong letters in grid");
        softAssert.assertEquals(appPage.rightGrid.getAllLetters(), expLetters, "wrong order of letters in grid");
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
        softAssert.assertTrue(appPage.leftTree.isAddDisabled(), "btn ADD not disabled for tree");
        softAssert.assertTrue(appPage.rightGrid.isAddDisabled(), "btn ADD not disabled for grid");
        softAssert.assertAll();
    }

    @Test
    public void dropDownFromTreeToGrid(){
    }

    @Test
    public void dropDownFromGridToTree(){
    }

}