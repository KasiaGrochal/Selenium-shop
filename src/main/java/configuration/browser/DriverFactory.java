package configuration.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class DriverFactory {

    public WebDriver getDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                return getChrome();
            case FIREFOX:
                return getFirefox();
            case IE:
                return getIe();
            case EDGE:
                return getEdge();
            default:
                return null;
        }
    }

    private WebDriver getEdge() {
        EdgeOptions options = new EdgeOptions();
        //add options
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();

    }

    private WebDriver getIe() {
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.ignoreZoomSettings();
        //add options
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver(ieOptions);
    }

    private WebDriver getFirefox() {
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        optionsFirefox.addArguments("start-maximized");
        return new FirefoxDriver(optionsFirefox);
    }

    private WebDriver getChrome() {
        ChromeOptions optionsChrome = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        optionsChrome.addArguments("start-maximized");
        return new ChromeDriver(optionsChrome);
    }
}
