package pages;

import selenium.helper.Browser;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.By;
import pages.shared.Page;

public class ContactPage extends Page {

    public ContactPage(Browser browser) {
        super(browser);
    }

    @FindBy(css = "input[formcontrolname='first_name'][data-test='first-name']")
    private WebElement firstNameField;

    @FindBy(css = "input[formcontrolname='last_name'][data-test='last-name']")
    private WebElement lastNameField;

    @FindBy(css = "input[formcontrolname='email'][data-test='email']")
    private WebElement emailField;

    @FindBy(css = "select[formcontrolname='subject'][data-test='subject']")
    private WebElement subjectDropdown; 

    @FindBys({
        @FindBy(css = "select[formcontrolname='subject'][data-test='subject'] option")
    })
    private List<WebElement> subjectOptions; 

    @FindBy(css = "textarea[formcontrolname='message'][data-test='message']")
    private WebElement messageTextField;

    @FindBy(css = "input[formcontrolname='attachment'][data-test='attachment']")
    private WebElement attachmentInput;

    @FindBy(css = "input[data-test='contact-submit'][type='submit']")
    private WebElement submitButton;

    @FindBy(css = "//div[contains(@class, 'alert-success') and contains(text(), 'Thanks for your message!')]")
    private WebElement successMessage;

    public void selectSubject(String subjectValue) {
        for (WebElement option : subjectOptions) {
            if (option.getText().trim().equalsIgnoreCase(subjectValue.trim())) {
                option.click();
                return;
            }
        }
        System.out.println("Subject not found: " + subjectValue);
    }

    public void setFirstNameField(String value) {
        firstNameField.sendKeys(value);
    }

    public void setLastNameField(String value) {
        lastNameField.sendKeys(value);
    }
    
    public void setEmailField(String value) {
        emailField.sendKeys(value);
    }

    public void setMessageField(String message) {
        messageTextField.sendKeys(message);
    }

    public void uploadFile(String filePath) {
        attachmentInput.sendKeys(filePath);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public boolean doesSuccessMessageExist() {
        return driver.findElements(By.xpath("//div[contains(@class, 'alert-success') and contains(text(), 'Thanks for your message!')]")).size() > 0;
    }
    
}