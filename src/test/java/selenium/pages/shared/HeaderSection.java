package pages.shared;

import selenium.helper.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class HeaderSection extends Element {

    public HeaderSection(Browser browser) {
        super(browser);
    }

    @FindBy(className = "navbar-brand")
    private WebElement brandIconButton;

    @FindBy(css = "a[data-test='nav-home']")
    private WebElement homeButton;

    @FindBy(css = "a[data-test='nav-categories']")
    private WebElement categoriesDropDown;

    @FindBys({
        @FindBy(css = "ul[aria-labelledby='nav-categories'] li a")
    })
    private List<WebElement> categoryLinks;    

    @FindBy(css = "a[data-test='nav-contact']")
    private WebElement contactButton;

    @FindBy(css = "a[data-test='nav-sign-in']")
    private WebElement signInButton;
    
    // Click on the brand icon button
    public void clickBrandIconButton() {
        brandIconButton.click();
    }

    // Click on the home button
    public void clickHomeButton() {
        homeButton.click();
    }

    // Click on the categories dropdown
    public void clickCategoriesDropDown() {
        categoriesDropDown.click();
    }

    // Click on the contact button
    public void clickContactButton() {
        contactButton.click();
    }

    // Click on the sign-in button
    public void clickSignInButton() {
        signInButton.click();
    }

    public void clickCategoryLink(String categoryName) {
        for (WebElement categoryLink : categoryLinks) {    
            if (categoryLink.getText().trim().equalsIgnoreCase(categoryName.trim())) {
                categoryLink.click();
                return;
            }
        }
        System.out.println("Category not found: " + categoryName);
    }
    
}
