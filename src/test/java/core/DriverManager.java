package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static Logger logger = LogManager.getLogger(DriverManager.class);
    private static WebDriver webDriver;

    public static void setupDriver() {
        String browserNameFromSystem = null;
        try {
            browserNameFromSystem = System.getProperty("browser");
            if (browserNameFromSystem == null) {
                browserNameFromSystem = "chrome";
            }
        } catch (Exception e) {
            browserNameFromSystem = "chrome";
        }
        webDriver = WebDriverFactory.create(browserNameFromSystem);
        Point point = new Point(-1380, 700); // open browser on second screen
        webDriver.manage().window().setPosition(point);
        webDriver.manage().window().setSize(new Dimension(1390, 810));
        logger.info("Драйвер поднят");
    }

    public static void quitDriver() {
        if (webDriver != null) {
            logger.info("Драйвер выключен");
            webDriver.quit();
        }
    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            setupDriver();
        }
        return webDriver;
    }
}
