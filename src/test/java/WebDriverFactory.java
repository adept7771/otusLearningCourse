import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {

    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static RemoteWebDriver create(String browserString, String optionsString) {
        WebDriver.Options options = convertOptionsStringToObject(optionsString);
        Browser browser = recognizeBrowser(browserString);
        switch (browser) {
            case chrome: {
                WebDriverManager.chromedriver().setup();
                logger.info("Драйвер Chrome формируется фабрикой");
                if (options != null) return new ChromeDriver((ChromeOptions) options);
                else return new ChromeDriver();
            }
            case opera: {
                WebDriverManager.operadriver().setup();
                logger.info("Драйвер Opera формируется фабрикой");
                if (options != null) return new OperaDriver((OperaOptions) options);
                else return new OperaDriver();
            }
            case firefox: {
                WebDriverManager.firefoxdriver().setup();
                logger.info("Драйвер Firefox формируется фабрикой");
                if (options != null) return new FirefoxDriver((FirefoxOptions) options);
                else return new FirefoxDriver();
            }
        }
        logger.error("Фабрике не удалось сформировать драйвер");
        return null;
    }

    private static WebDriver.Options convertOptionsStringToObject(String incomingString){
        return null;
    }

    private static Browser recognizeBrowser(String incomingBrowserName) {
        if (incomingBrowserName == null) {
            return Browser.firefox;
        }
        String browserName = incomingBrowserName.toLowerCase();
        if (browserName.contains("chrome")) {
            return Browser.chrome;
        }
        if (browserName.contains("opera")) {
            return Browser.opera;
        } else {
            return Browser.firefox;
        }
    }
}
