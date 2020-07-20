import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public static RemoteWebDriver create(String browserOptionFromSystem){
        return create(recognizeBrowser(browserOptionFromSystem), null);
    }

    public static RemoteWebDriver create(Browser browser){
        return create(browser, null);
    }

    public static RemoteWebDriver create(Browser browser, WebDriver.Options options){
        if(browser.equals(Browser.chrome)){
            WebDriverManager.chromedriver().setup();
            ChromeDriver chromeDriver = null;
            if(options != null){
                ChromeOptions chromeOptions = (ChromeOptions) options;
                chromeDriver = new ChromeDriver(chromeOptions);
            }
            else {
                chromeDriver = new ChromeDriver();
            }
            logger.info("Драйвер Chrome сформирован фабрикой");
            return chromeDriver;
        }
        if(browser.equals(Browser.opera)){
            WebDriverManager.operadriver().setup();
            OperaDriver operaDriver = null;
            if(options != null){
                OperaOptions operaOptions = (OperaOptions) options;
                operaDriver = new OperaDriver(operaOptions);
            }
            else {
                operaDriver = new OperaDriver();
            }
            logger.info("Драйвер Opera сформирован фабрикой");
            return operaDriver;
        }
        else {
            WebDriverManager.firefoxdriver().setup();
            FirefoxDriver firefoxDriver = null;
            if(options != null){
                FirefoxOptions firefoxOptions = (FirefoxOptions) options;
                firefoxDriver = new FirefoxDriver(firefoxOptions);
            }
            else {
                firefoxDriver = new FirefoxDriver();
            }
            logger.info("Драйвер Firefox сформирован фабрикой");
            return firefoxDriver;
        }
    }

    private static Browser recognizeBrowser(String incomingBrowserName){
        if(incomingBrowserName == null){
            return Browser.firefox;
        }
        String browserName = incomingBrowserName.toLowerCase();
        if(browserName.contains("chrome")){
            return Browser.chrome;
        }
        if(browserName.contains("opera")){
            return Browser.opera;
        }
        else {
            return Browser.firefox;
        }
    }
}
