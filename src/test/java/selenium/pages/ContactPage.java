package pages;

import selenium.helper.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.shared.Page;

public class ContactPage extends Page {

    public ContactPage(Browser browser) {
        super(browser);
    }

    @FindBy(css = "input[formcontrolname='first_name'][data-test='first-name']")
    private WebElement firstNameField;

    public void setFirstNameField(String value) {
        firstNameField.sendKeys(value);
    }
}