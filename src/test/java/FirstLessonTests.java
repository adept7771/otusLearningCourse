import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstLessonTests {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(FirstLessonTests.class);

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @Test
    public void myFirstTest(){
        driver.get("https://otus.ru");
        logger.info("Открыта страница отус");
        Assert.assertTrue("Тайтл не содержит заданный текст.",
                driver.getTitle().contains(
                        "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям"));
    }

    @After
    public void setDown(){
        if(driver != null){
            logger.info("Драйвер выключен");
            driver.quit();
        }
    }
}
