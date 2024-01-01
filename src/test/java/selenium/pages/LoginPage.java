package pages;

import selenium.helper.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.shared.Page;

public class LoginPage extends Page {

    public LoginPage(Browser browser) {
        super(browser);
    }

    @FindBy(css = "input[formcontrolname='email'][data-test='email']")
    private WebElement emailField;

    @FindBy(css = "input[formcontrolname='password'][data-test='password']")
    private WebElement passwordField;

    @FindBy(css = "input[data-test='login-submit'].btnSubmit[type='submit']")
    private WebElement loginSubmitButton;

    public void setEmailField(String value) {
        emailField.sendKeys(value);
    }

    public void setPasswordField(String value) {
        passwordField.sendKeys(value);
    }

    public void clickLoginSubmitButton() {
        loginSubmitButton.click();
    }
}