import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class lesson6tests {

    private WebDriver webDriver;
    private Logger logger = LogManager.getLogger(lesson6tests.class);

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
        logger.info("Драйвер поднят");
    }

    @Test
    public void myFirstTest() {
        webDriver.get("https://market.yandex.kz/");
        closeAlertIfAppeared();
        clickWithWait("//span[text()='Каталог товаров']");
        clickWithWait("//a[text()='Мобильные телефоны']");
        clickWithWait("//label//span[text()='Xiaomi']");
        clickWithWait("//label//span[text()='Apple']");
        clickWithWait("//button[@data-autotest-id='dprice']");
        WebElement redmiFirstProperPhoneWebElement = null, appleFirstProperPhoneWebElement = null;

        int catalogPagesAmount = findAllWebElements
                ("//a[contains(@class, 'button_theme_pseudo')]/span[not(contains(text(),'0')) and " +
                        "not(contains(text(),'Назад')) and not(contains(text(),'Вперед')) " +
                        "and not(contains(text(),'Показать'))]").size();
        if(catalogPagesAmount == 0){
            catalogPagesAmount += 1;
                // в ситуации, когда пагинация отсутсвует
        }

        for (int i = 0; i < catalogPagesAmount - 1; i++) {
            ArrayList<WebElement> firstPageAllComparablePhones = findAllWebElements
                    ("//a[contains(text(), 'предложения')]/parent::div/parent::div/parent::article//h3[@data-zone-name='title']/a/span");
            // такой страшный и длинный икспас нужен только по причине
            // того, что добавить к сравнению мы можем только те товары, предложений которых больше чем 1. То есть надо
            // искать путь к строчке предложений, а потом подниматься наверх до тайтла. В задании это не предусмотрено.

            if(redmiFirstProperPhoneWebElement == null){
                redmiFirstProperPhoneWebElement = getElementWithContainsTextFromList(firstPageAllComparablePhones,
                        "Redmi");

                String smartPhoneTitle = getElementWithContainsTextFromList(firstPageAllComparablePhones,
                        "Redmi").getText();
                smartPhoneTitle = smartPhoneTitle.replace("Смартфон", "Смартфон ");
                // Redmi ищем, потому что в каталоге марка Ксиаоми, а модель Редми
                // строим локатор добавления модели к сравнению, если модель можно сравнить и она существует
                // при формировании имени товара почему-то вставляется один лишний пробел после слова смартфон

                if(redmiFirstProperPhoneWebElement != null){
                    String xpathForRedmiCompareButton = "//span[contains(text(), '" + smartPhoneTitle
                            + "')]/ancestor::article//div[contains(@aria-label, 'сравнению')]";
                    clickWithWait(xpathForRedmiCompareButton);
                }
            }
            if(appleFirstProperPhoneWebElement == null){
                appleFirstProperPhoneWebElement = getElementWithContainsTextFromList(firstPageAllComparablePhones,
                        "Apple");
                String smartPhoneTitle = appleFirstProperPhoneWebElement.getText().replaceFirst("Cмартфон", "Смартфон  ");
                if(appleFirstProperPhoneWebElement != null){
                    String xpathForAppleCompareButton = "//span[contains(text(), '" + smartPhoneTitle
                            + "')]/ancestor::article//div[contains(@aria-label, 'сравнению')]";
                    clickWithWait(xpathForAppleCompareButton);
                }
            }
            // на странице может быть не найден искомый телефон, поэтому надо перебирать все предложения по странично
            // если телефон так же не будет найден на последующих страницах то тест должен упасть
            if (redmiFirstProperPhoneWebElement != null && appleFirstProperPhoneWebElement != null) {
                logger.info("Необходимые модели найдены. Заканчиваю перебор каталога.");
                break;
            }
            if(i == catalogPagesAmount - 1){
                if(redmiFirstProperPhoneWebElement == null || appleFirstProperPhoneWebElement == null){
                    Assert.fail("Перебраны все страницы каталога, однако требуемые модели не были найдены" +
                            "для проведения сравнения.");
                }
            }
            findAllWebElements
                    (30L, "//a[contains(@class, 'button_theme_pseudo')]/span[not(contains(text(),'0')) and " +
                            "not(contains(text(),'Назад')) and not(contains(text(),'Вперед')) " +
                            "and not(contains(text(),'Показать'))]").get(i).click(); // итерирование страниц каталога
        }




        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void clickWithWait(long timeToWait, String xpath) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        logger.info("Попытка поиска элемента и проведения клика по пути: " + xpath);
        wait.withMessage("WebElement can't be found by: " + xpath);
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
    }

    public void clickWithWait(String xpath) {
        clickWithWait(5L, xpath);
    }

    public ArrayList<WebElement> findAllWebElements(long timeToWait, String xpath) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        ArrayList<WebElement> webElements = (ArrayList<WebElement>)
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        logger.info("Поиск всех элементов по пути: " + xpath);
        return webElements;
    }

    public ArrayList<WebElement> findAllWebElements(String xpath) {
        return findAllWebElements(10L, xpath);
    }

    public void closeAlertIfAppeared() {
        getReadyState();
        try {
            clickWithWait(2L,
                    "//button[contains(@class, 'lg-cc__button_type_action') and (text()='Принять')]");
        } catch (WebDriverException e) {
            logger.info("Окно принять условия не появилось.");
        }
    }

    public WebElement getElementWithContainsTextFromList(ArrayList<WebElement> listOfWebElements,
                                                         String textOfElement) {
        for (int i=0; i < listOfWebElements.size(); i++) {
            try {
                if (listOfWebElements.get(i).getText().contains(textOfElement)) {
                    return listOfWebElements.get(i);
                }
            }
            catch (StaleElementReferenceException e){
                continue;
            }
        }
        return null;
    }

    public void getReadyState(){
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
    }

    @After
    public void setDown() {
        if (webDriver != null) {
            logger.info("Драйвер выключен");
            webDriver.quit();
        }
    }
}
