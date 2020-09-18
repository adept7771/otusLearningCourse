package pages;

import core.TestsCore;
import org.openqa.selenium.By;

public class PersonalCabinetMenuPage extends TestsCore {

    public By aboutMySelfButton = By.xpath("(//div[@class='nav__items']//a[@title='О себе'])[1]");
    public By myCoursesButton = By.xpath("//div[@class='inline-block-promo' and text()='Мои курсы']");
    public By payButton = By.xpath("(//div[@class='nav__items']//a[@title='Оплата'])[1]");
}
