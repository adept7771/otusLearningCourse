package pages;

import core.TestsCore;
import org.openqa.selenium.By;

public class MainPage extends TestsCore {

    public By statisticsBlock = By.xpath("//div[@class='transitional-main__stat']");
    public By studentStatistic = By.xpath("//div[@class='transitional-main__stat-title' and contains(.,'студент')]");
    public By groupStatistic = By.xpath("//div[@class='transitional-main__stat-title' and contains(.,'груп')]");
    public By teacherStatistic = By.xpath("//div[@class='transitional-main__stat-title' and contains(.,'препод')]");
    public By testingCoursesLink = By.xpath("(//div[@class='nav__items course-categories__nav']//a[@title='Тестирование'])[1]");
    public By qaJavaCourseLink = By.xpath("(//a[contains(@href,'java-qa-engineer')])[2]");
    public By gameTestingCourseLink = By.xpath("(//div[@class='lessons__new-item-container']//div[contains(., 'Тестирование игр')])[1]");
    public By bestTeacherAtCourse = By.xpath("//div[@class='course-teacher-item__name' and contains(.,'Чернышева')]");
}
