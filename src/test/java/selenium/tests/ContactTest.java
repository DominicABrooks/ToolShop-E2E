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
        browser.ContactPage().setLastNameField("Last");
        browser.ContactPage().setEmailField("Email@host.com");
        browser.ContactPage().selectSubject("Warranty");
        browser.ContactPage().setMessageField("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*() ");
        String filePath = System.getProperty("user.dir") + "/src/test/java/resources/files/normal_test.txt";
        browser.ContactPage().uploadFile(filePath);
        browser.ContactPage().clickSubmitButton();

        boolean success = browser.ContactPage().doesSuccessMessageExist();
        Assert.assertTrue(success);

        browser.takeScreenshot("example.jpg");
    }

    @AfterClass
    public void tearDown() {
        browser._driver.quit();
    }
}