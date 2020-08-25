package pages;

import org.openqa.selenium.By;

public class HeaderMenuPage {

    public By loginButton = By.xpath("//button[@data-modal-id='new-log-reg']");

    public By myCoursesButton = By.xpath("//a[@href='/learning/' and contains(@class, 'button_purple')]");
}
