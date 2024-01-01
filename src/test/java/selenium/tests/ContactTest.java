package test.java.selenium.tests;

import org.testng.annotations.*;
import org.testng.Assert;
import selenium.helper.Browser;

public class ContactTest {

    Browser browser;

    @BeforeClass
    @Parameters({"browserName", "baseUrl"})
    public void setUp(String browserName, String baseUrl) {
        browser = new Browser(browserName, baseUrl);
        browser.navigateToBaseUrl();
    }

    @Test
    public void firstNameFieldTest() throws InterruptedException {
        browser.navigateTo("https://practicesoftwaretesting.com/#/contact");
        browser.ContactPage().setFirstNameField("First");
        // Assert.assertEquals(assert something);
    }

    @AfterClass
    public void tearDown() {
        browser._driver.quit();
    }
}