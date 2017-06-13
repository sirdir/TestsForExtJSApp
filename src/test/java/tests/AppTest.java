package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.AppPage;

public class AppTest extends BaseTest{


    @Test
    public void test1(){
        driver.get(url);
        AppPage appPage = PageFactory.initElements(driver, AppPage.class);
        appPage.rightGrid.selectByName("Z");
    }

}