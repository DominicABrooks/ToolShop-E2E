package pages;

import selenium.helper.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.shared.Page;

public class HomePage extends Page {

    public HomePage(Browser browser) {
        super(browser);
    }

    @FindBy(css = "input[formcontrolname='query'][data-test='search-query']")
    private WebElement searchField;

    @FindBy(css = "button[data-test='search-submit']")
    private WebElement searchButton;

    public void setSearchField(String value) {
        searchField.sendKeys(value);
    }

    public void clickOnSearchButton() {
        searchButton.click();
    }

    @FindBy(css = "div.col-md-9 > h3")
    private WebElement searchForText;

    // this element only appears after the search button is clicked
    public String getSearchedForText() {
        return searchForText.getText();
    }

}