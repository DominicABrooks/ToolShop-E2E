package test.java.selenium.tests;

import org.testng.annotations.*;
import org.testng.Assert;
import selenium.helper.Browser;

public class HomeTest {

    Browser browser;

    @BeforeClass
    @Parameters({"browserName", "baseUrl"})
    public void setUp(String browserName, String baseUrl) {
        browser = new Browser(browserName, baseUrl);
        browser.navigateToBaseUrl();
    }

    @Test
    public void searchTest() throws InterruptedException {
        browser.HomePage().setSearchField("Pliers");
        browser.HomePage().clickOnSearchButton();
        String expectedHeaderText = "Searched for: Pliers";
        String actualHeaderText = browser.HomePage().getSearchedForText();
        Assert.assertEquals(expectedHeaderText, actualHeaderText);
    }

    @AfterClass
    public void tearDown() {
        browser._driver.quit();
    }
}