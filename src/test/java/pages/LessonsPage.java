package pages;

import core.TestsCore;
import org.openqa.selenium.By;

public class LessonsPage extends TestsCore {

    public By javaCourseButton = By.xpath("//div[@class='learning-item__title' and text()='Java QA Engineer']");
    public By secondMonthButton = By.xpath("//div[contains(@data-show, 'data-month=\"2\"')]");
    public By timerIconForPageObjectLesson = By.xpath("//span[@class='learning-near__header-text' and " +
            "text()='Page object']/following-sibling::span[contains(@class, 'learning-near__header-fire')]");
}
