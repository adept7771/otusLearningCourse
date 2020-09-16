package pages;

import org.openqa.selenium.By;

public class MainPage {

    public By statisticsBlock = By.xpath("//div[@class='transitional-main__stat']");
    public By studentStatistic = By.xpath("//div[@class='transitional-main__stat-title' and contains(.,'студент')]");
    public By groupStatistic = By.xpath("//div[@class='transitional-main__stat-title' and contains(.,'груп')]");
    public By teacherStatistic = By.xpath("//div[@class='transitional-main__stat-title' and contains(.,'препод')]");


}
