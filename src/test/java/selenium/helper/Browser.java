package selenium.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.util.concurrent.TimeUnit;
import pages.HomePage;
import pages.ContactPage;
import pages.LoginPage;
import pages.shared.HeaderSection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browser {

    public Browser(String browserName, String baseUrl) {
        setBrowser(browserName);
        setBaseUrl(baseUrl);
        Initialize(getBrowser()); 
    }

    private void Initialize(String browser) {
        switch (browser) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                _driver = new ChromeDriver(chromeOptions);
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                _driver = new FirefoxDriver(firefoxOptions);
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                _driver = new EdgeDriver();
                break;
            default:
                System.out.println("Invalid browser passed in: " + browser);
                break;
        }
        _driver.manage().window().maximize();
        _driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void navigateTo(String url) {
        _driver.get(url);
    }

    public void navigateToBaseUrl() {
        _driver.get(getBaseUrl());
    }

    public String getBrowser() {
        return this.browserName;
    }

    private void setBrowser(String browserName) {
        this.browserName = browserName;
    }

    private void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getCurrentUrl() {
        return _driver.getCurrentUrl();
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public HomePage HomePage() {
        if (homePage == null) {
            homePage = new HomePage(this);
        }
        return homePage;
    }

    public ContactPage ContactPage() {
        if (contactPage == null) {
            contactPage = new ContactPage(this);
        }
        return contactPage;
    }

    public LoginPage LoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(this);
        }
        return loginPage;
    }

    // Public properties
    public WebDriver _driver;

    // Private properties
    private String browserName;
    private String baseUrl;
    private HomePage homePage;
    private ContactPage contactPage;
    private LoginPage loginPage;
}