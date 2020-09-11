import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class lesson6tests {

    private WebDriver webDriver;
    private Logger logger = LogManager.getLogger(lesson6tests.class);


    @Test
    public void myFirstTest() {
        webDriver.get("https://market.yandex.ru");
        closeAlertIfAppeared();
        clickWithWait("//span[contains(text(), 'Электроника')]");
        clickWithWait("//a[text()='Мобильные телефоны']");
        clickWithWait("//label//span[text()='Xiaomi']");
        clickWithWait("//label//span[text()='Apple']");
        clickWithWait("//button[@data-autotest-id='dprice']");

        WebElement xiaomiFirstProperPhoneWebElement = null, appleFirstProperPhoneWebElement = null;
        String xiaomiPhoneName = null, iphonePhoneName = null;

        String catalogPagesXpath = "//a[contains(@aria-label, 'Страница')]";
        int catalogPagesAmount = findAllWebElements(catalogPagesXpath).size();
        if (catalogPagesAmount == 0) {
            catalogPagesAmount += 1;
            // в ситуации, когда пагинация отсутсвует
        }

        for (int i = 1; i <= catalogPagesAmount - 1; i++) { // перебор каталога в поиске нужно модели
            if (xiaomiFirstProperPhoneWebElement != null && appleFirstProperPhoneWebElement != null) {
                break;
            }
            if (i != 1) { // catalog pages navigation
                String xpathForNavigationButtons = addIndexToXpath("//a[contains(@aria-label, 'Страница')]", i);
                clickWithWait(xpathForNavigationButtons);
            }

            WebDriverWait wait = new WebDriverWait(webDriver, 10L);
            if (i != catalogPagesAmount) { // ожидание загрузки каталога
                String xpathForShowMoreButton = "//button[contains(text(), 'Показать ещё')]";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathForShowMoreButton)));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathForShowMoreButton)));
            } else {
                String xpathForBackButton = "//span[contains(text(), 'Назад')]";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathForBackButton)));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathForBackButton)));
            }

            String xpathH3ComparablePhones = "//a[contains(text(), 'предложени')]/parent::div/parent::div/parent::article//h3[@data-zone-name='title']";
            // такой страшный и длинный икспас нужен только по причине
            // того, что добавить к сравнению мы можем только те товары, предложений которых больше чем 1. То есть надо
            // искать путь к строчке предложений, а потом подниматься наверх до тайтла. В задании это не предусмотрено.

            int comparablePhonesAmount = findAllWebElements(xpathH3ComparablePhones).size();
            Assert.assertTrue("В каталоге отсутсвуют модели, доступные для сравнения",
                    comparablePhonesAmount > 0);

            // перебор всех телефонов доступных к сравнению на текущей страничке каталога
            // для Xiaomi:
            if (xiaomiFirstProperPhoneWebElement == null) {
                for (int a = 1; a < comparablePhonesAmount; a++) {

                    String titleOfPhone = findElement(addIndexToXpath(xpathH3ComparablePhones, a), 10L)
                            .getText();

                    if (xiaomiFirstProperPhoneWebElement == null && titleOfPhone.contains("Redmi")) {
                        xiaomiFirstProperPhoneWebElement = findElement
                                (addIndexToXpath(xpathH3ComparablePhones, a), 10L);

                        String catalogWindow = webDriver.getWindowHandle();
                        String modifiedXpathForComparablePhones = addIndexToXpath(xpathH3ComparablePhones, a);
                        // формируем xpath для модели, подходящей для перехода на ее страничку
                        clickWithWait(modifiedXpathForComparablePhones);
                        switchToNewWindowExceptMentioned(catalogWindow);

                        findElement("//div[@data-zone-name='image']//img", 15L);
                        String phoneNameOnPhonePage =
                                findElement("//div[@data-apiary-widget-id='/content/productCardTitle']//h1",
                                        10L).getText();
                        xiaomiPhoneName = phoneNameOnPhonePage;
                        clickWithWait("//div[@data-apiary-widget-id='/content/productCardTitle']" +
                                "//div[contains(@aria-label, 'сравнению')]/div");

                        String fullNameInCompareXpath = "//div[contains(text(), '" + phoneNameOnPhonePage +
                                " добавлен к сравнению')]";
                        Assert.assertNotNull("Сообщение добавить к сравнению не появилось или содержит ошибку",
                                findElement(fullNameInCompareXpath, 10L));
                        webDriver.close();
                        switchToNewWindowExceptMentioned(catalogWindow);
                        webDriver.switchTo().window(catalogWindow);
                        break;
                    }
                }
            }
            if (appleFirstProperPhoneWebElement == null) { // для Apple
                for (int a = 1; a < comparablePhonesAmount; a++) {
                    String titleOfPhone = "";
                    try {
                        titleOfPhone = findElement(addIndexToXpath(xpathH3ComparablePhones, a), 10L)
                                .getText();
                    }
                    catch (StaleElementReferenceException e){
                        titleOfPhone = findElement(addIndexToXpath(xpathH3ComparablePhones, a), 10L)
                                .getText();
                    }

                    if (appleFirstProperPhoneWebElement == null && titleOfPhone.contains("iPhone")) {
                        appleFirstProperPhoneWebElement = findElement
                                (addIndexToXpath(xpathH3ComparablePhones, a), 10L);

                        String catalogWindow = webDriver.getWindowHandle();
                        String xpathImgComparablePhones = (addIndexToXpath("//a[contains(@href, 'product--smartfon') and @data-zone-name='picture']",
                                a));
                        clickWithWait(xpathImgComparablePhones);
                        switchToNewWindowExceptMentioned(catalogWindow);

                        try {
                            System.out.println(webDriver.getCurrentUrl());
                            findElement("//div[@data-zone-name='image']//img", 5L);
                        }
                        catch (TimeoutException e){
                            clickWithWait(xpathImgComparablePhones);
                            switchToNewWindowExceptMentioned(catalogWindow);
                            System.out.println(webDriver.getCurrentUrl());
                            findElement("//div[@data-zone-name='image']//img", 5L);
                        }
                        // часто именно на айфоне не происходит прокликивания перехода. Хотя селениум рапортует,
                        // что клик произведен :(

                        String phoneNameOnPhonePage =
                                findElement("//div[@data-apiary-widget-id='/content/productCardTitle']//h1",
                                        10L).getText();
                        iphonePhoneName = phoneNameOnPhonePage;
                        clickWithWait("//div[@data-apiary-widget-id='/content/productCardTitle']" +
                                "//div[contains(@aria-label, 'сравнению')]/div");

                        String fullNameInCompareXpath = "//div[contains(text(), '" + phoneNameOnPhonePage +
                                " добавлен к сравнению')]";
                        Assert.assertNotNull("Сообщение добавить к сравнению не появилось или содержит ошибку",
                                findElement(fullNameInCompareXpath, 10L));
                        webDriver.close();
                        switchToNewWindowExceptMentioned(catalogWindow);
                        webDriver.switchTo().window(catalogWindow);
                        break;
                    }
                }
            }
        }
        clickWithWait("//a[contains(@href, 'compare')]");
        Assert.assertNotNull("Xiaomi не найден в сравнении",
                findElement("//a[contains(text(), '" + xiaomiPhoneName + "')]", 10L));
        Assert.assertNotNull("iPhone не найден в сравнении",
                findElement("//a[contains(text(), '" + iphonePhoneName + "')]", 10L));
        clickWithWait("//button[contains(text(), 'Все характеристики')]");
        Assert.assertNotNull("Операционная система не найдена в сравнении",
                findElement("//div[contains(text(), 'Операционная система')]", 10L));
        clickWithWait("//button[contains(text(), 'Различающиеся характеристики')]");
        Assert.assertFalse("Операционная система все так же видна в различающихся характеристиках",
                isElementVisible("//button[contains(text(), 'Операционная система')]", 3L));
    }

    // ================================================================

    @Before
    public void setUp() {
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
        Point point = new Point(-1380, 650); // open browser on second screen
        webDriver.manage().window().setPosition(point);
        webDriver.manage().window().setSize(new Dimension(1390, 810));
        logger.info("Драйвер поднят");
    }

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
        waitPreloaderInvisible();
    }

    public void waitPreloaderInvisible(){
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath("/html/body/div[3]/div[5]/div[2]/div/div[1]/div/div/div[2]/div/div")));
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

    @After
    public void setDown() {
        if (webDriver != null) {
            logger.info("Драйвер выключен");
            webDriver.quit();
        }
    }
}
