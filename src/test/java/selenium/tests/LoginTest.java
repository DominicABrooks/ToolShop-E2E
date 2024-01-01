package test.java.selenium.tests;

import org.testng.annotations.*;
import org.testng.Assert;
import selenium.helper.Browser;

public class LoginTest {

    Browser browser;

    @BeforeClass
    @Parameters({"browserName", "baseUrl"})
    public void setUp(String browserName, String baseUrl) {
        browser = new Browser(browserName, baseUrl);
        browser.navigateToBaseUrl();
    }

    @Test
    public void loginTest() throws InterruptedException {
        browser.navigateTo("https://practicesoftwaretesting.com/#/auth/login");
        browser.LoginPage().setEmailField("email@example.com");
        browser.LoginPage().setPasswordField("password123");
        browser.LoginPage().clickLoginSubmitButton();
        // Assert.assertEquals(assert something);
    }

    @AfterClass
    public void tearDown() {
        browser._driver.quit();
    }
}