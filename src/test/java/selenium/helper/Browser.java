package selenium.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.util.concurrent.TimeUnit;
import pages.HomePage;
import pages.ContactPage;
import pages.LoginPage;
import pages.shared.HeaderSection;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void takeScreenshot(String screenshotName) {
        try {
            TakesScreenshot screenshotDriver = (TakesScreenshot) _driver;
            File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

            // Generate a timestamped screenshot name
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String finalScreenshotName = screenshotName + "_" + timestamp + ".png";

            String screenshotDestination = finalScreenshotName;

            // Copy the screenshot to the destination
            Files.copy(screenshotFile.toPath(), Path.of(screenshotDestination), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot captured: " + finalScreenshotName);
        } catch (WebDriverException | IOException e) {
            e.printStackTrace();
        }
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