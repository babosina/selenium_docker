package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import util.Config;
import util.Constants;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {

    protected WebDriver driver;
    public static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void setConfigObject() throws Exception {
        Config.initialize();
    }

    @BeforeTest
    public void setDriver() throws MalformedURLException {
        if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))) {
            this.driver = this.getRemoteDriver();
            this.driver.manage().window().maximize();
        } else {
            this.driver = this.getLocalDriver();
            this.driver.manage().window().maximize();
        }
        // driver setup
//        if (Boolean.getBoolean("selenium.grid.enabled")) {
//            this.driver = this.getRemoteDriver();
//            this.driver.manage().window().maximize();
//        } else {
//            this.driver = this.getLocalDriver();
//            this.driver.manage().window().maximize();
//        }

//        WebDriverManager.chromedriver().setup();
//        this.driver = new ChromeDriver();
//        this.driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
            capabilities = new FirefoxOptions();
        }

//        if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
//            capabilities = new ChromeOptions();
//        } else {
//            capabilities = new FirefoxOptions();
//        }
        // http://localhost:4444/wd/hub
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        LOGGER.info("URL is: {}", url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }
}
