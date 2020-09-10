import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class DummyTest {

    private WebDriver webDriver;
    private Logger logger = LogManager.getLogger(DummyTest.class);

    @Before
    public void setUp(){
        String browserNameFromSystem, browserOptions = null;
        try {
            browserNameFromSystem = System.getProperty("browser");
        }
        catch (Exception e){
            browserNameFromSystem = "firefox";
        }
        try {
            browserOptions = System.getProperty("options");
        }
        catch (Exception ignored){
        }
        webDriver = WebDriverFactory.create(browserNameFromSystem, browserOptions);
        logger.info("Драйвер поднят");
    }

    @Test
    public void myFirstTest(){
        webDriver.get("https://otus.ru");
        logger.info("Открыта страница отус");
        Assert.assertTrue("Тайтл не содержит заданный текст.",
                webDriver.getTitle().contains(
                        "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям"));
    }

    @After
    public void setDown(){
        if(webDriver != null){
            logger.info("Драйвер выключен");
            webDriver.quit();
        }
    }
}
