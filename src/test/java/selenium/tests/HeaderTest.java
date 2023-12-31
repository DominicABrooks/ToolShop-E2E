package test.java.selenium.tests;

import org.testng.annotations.*;
import org.testng.Assert;
import selenium.helper.Browser;
import pages.shared.HeaderSection;
import pages.shared.Page;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HeaderTest {
    Browser browser;
    Page page;
    HeaderSection headerSection;

    @BeforeClass
    @Parameters({"browserName", "baseUrl"})
    public void setUp(String browserName, String baseUrl) {
        browser = new Browser(browserName, baseUrl);
        browser.navigateToBaseUrl();
        page = new Page(browser);
        headerSection = page.HeaderSection();
    }

    private void waitForUrl(String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(browser._driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    @Test
    public void brandIconTest() {
        headerSection.clickBrandIconButton();
        waitForUrl("https://practicesoftwaretesting.com/#/");
        Assert.assertEquals(browser.getCurrentUrl(), "https://practicesoftwaretesting.com/#/");
    }

    @Test
    public void homeButtonTest() {
        headerSection.clickHomeButton();
        waitForUrl("https://practicesoftwaretesting.com/#/");
        Assert.assertEquals(browser.getCurrentUrl(), "https://practicesoftwaretesting.com/#/");
    }

    @Test
    public void contactButtonTest() {
        headerSection.clickContactButton();
        waitForUrl("https://practicesoftwaretesting.com/#/contact");
        Assert.assertEquals(browser.getCurrentUrl(), "https://practicesoftwaretesting.com/#/contact");
    }

    @Test
    public void signInButtonTest() {
        headerSection.clickSignInButton();
        waitForUrl("https://practicesoftwaretesting.com/#/auth/login");
        Assert.assertEquals(browser.getCurrentUrl(), "https://practicesoftwaretesting.com/#/auth/login");
    }

    @Test
    public void navigateToCategoryTest() {
        clickLinkAndAssertUrl("Hand Tools", "https://practicesoftwaretesting.com/#/category/hand-tools");
        clickLinkAndAssertUrl("Power Tools", "https://practicesoftwaretesting.com/#/category/power-tools");
        clickLinkAndAssertUrl("Other", "https://practicesoftwaretesting.com/#/category/other");
        clickLinkAndAssertUrl("Special Tools", "https://practicesoftwaretesting.com/#/category/special-tools");
        clickLinkAndAssertUrl("Rentals", "https://practicesoftwaretesting.com/#/rentals");
    }

    private void clickLinkAndAssertUrl(String linkText, String expectedUrl) {
        headerSection.clickCategoriesDropDown();
        headerSection.clickCategoryLink(linkText);
        waitForUrl(expectedUrl);
        Assert.assertEquals(browser.getCurrentUrl(), expectedUrl);
    }

    @AfterClass
    public void tearDown() {
        browser._driver.quit();
    }
}
