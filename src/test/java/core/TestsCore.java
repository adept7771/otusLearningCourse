package core;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestsCore extends TestCase {

    private Logger logger = LogManager.getLogger(TestsCore.class);
    private WebDriver webDriver;

    @Override
    protected void setUp(){
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
        //Point point = new Point(-1290, 100); // open browser on second screen
        //webDriver.manage().window().setPosition(point);
        //webDriver.manage().window().setSize(new Dimension(1280, 768));
        logger.info("Драйвер поднят");
        try {
            super.setUp();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    protected void tearDown(){
        if (webDriver != null) {
            logger.info("Драйвер выключен");
            webDriver.quit();
        }
        try {
            super.tearDown();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    // ================================================================

    public void scrollToElement(long timeToWait, String xpath) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public void switchToNewWindowExceptMentioned(String windowToExcept) {
        Set windowHandles = webDriver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        while (iterator.hasNext()) {
            String window = iterator.next();
            if (!window.equals(windowToExcept)) {
                webDriver.switchTo().window(window);
            }
        }
    }

    public void scrollToElement(long timeToWait, WebElement webElement) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        //wait.until(ExpectedConditions.presenceOfElementLocated((By) webElement));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public void clickWithWait(long timeToWait, String xpath) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        logger.info("Попытка поиска элемента и проведения клика по пути: " + xpath);
        wait.withMessage("WebElement can't be found by: " + xpath);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);",
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        int maxWaitForAvoidStaleElementException = (int) timeToWait;
        for (int i = 0; i < maxWaitForAvoidStaleElementException; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
                return;
            } catch (StaleElementReferenceException e) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                waitStatic(1000);
            }
        }
    }

    public WebElement findElement(String xpath, long timeToWait) {
        getReadyState();
        logger.info("Ждем элемент по xpath: " + xpath);
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        int maxWaitForAvoidStaleElementException = (int) timeToWait;
        for (int i = 0; i < maxWaitForAvoidStaleElementException; i++) {
            try {
                return webDriver.findElement(By.xpath(xpath));
            } catch (StaleElementReferenceException e) {
                waitStatic(1000);
                continue;
            }
        }
        return null;
    }

    public void clickWithWait(String xpath) {
        clickWithWait(10L, xpath);
    }

    public List<WebElement> findAllWebElements(long timeToWait, String xpath) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        logger.info("Поиск всех элементов по пути: " + xpath);
        int maxWaitForAvoidStaleElementException = (int) timeToWait;
        for (int i = 0; i < maxWaitForAvoidStaleElementException; i++) {
            try {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
                return webDriver.findElements(By.xpath(xpath));
            } catch (StaleElementReferenceException e) {
                waitStatic(1000);
                continue;
            }
        }
        return null;
    }

    public List<WebElement> findAllWebElements(String xpath) {
        return findAllWebElements(10L, xpath);
    }

    public boolean isElementVisible(String xpath, long timeToWait) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        try {
            waitStatic((int) timeToWait);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (ElementNotVisibleException e) {
            return false;
        }
        catch (TimeoutException e){
            return false;
        }
    }

    public void closeAlertIfAppeared() {
        getReadyState();
        try {
            clickWithWait(1L,
                    "//button[contains(@class, 'lg-cc__button_type_action') and (text()='Принять')]");
        } catch (WebDriverException e) {
            logger.info("Окно принять условия не появилось.");
        }
    }

    public void getAndClickElementWithContainsTextFromList(List<WebElement> listOfWebElements,
                                                           String textOfElement) {
        for (int i = 0; i < listOfWebElements.size(); i++) {
            try {
                if (listOfWebElements.get(i).getText().contains(textOfElement)) {
                    logger.info("Провожу клик по элементу " + listOfWebElements.get(i).toString());
                    scrollToElement(5L, listOfWebElements.get(i));
                    listOfWebElements.get(i).click();
                    return;
                }
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }
    }

    public void getReadyState() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
    }

    public String addIndexToXpath(String xpath, int i) {
        return "(" + xpath + ")[" + i + "]";
    }

    public void waitStatic(int timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
