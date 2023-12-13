package apiTest.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ApplicationProperties;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            setupDriver();
        }
        return webDriver.get();
    }

    private static void setupDriver() {
        switch (ApplicationProperties.get("browserType").toLowerCase()) {
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                webDriver.set(new FirefoxDriver());
                break;
            }
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
                WebDriver driver = new ChromeDriver(options);
                webDriver.set(driver);
                break;
            }
            case "edge": {
                WebDriverManager.edgedriver().setup();
                webDriver.set(new EdgeDriver());
                break;
            }
            default: {
                System.out.println("Browser type is not supported!");
            }
        }
        webDriver.get().manage().window().maximize();
    }
    public static void teardown() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }
}
