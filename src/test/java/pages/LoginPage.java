package pages;

import org.openqa.selenium.By;

public class LoginPage {

    public By emailInput = By.xpath("//div[@class='new-log-reg__body']" +
            "//input[@name='email' and @placeholder='Электронная почта']");

    public By passwordInput = By.xpath("//div[@class='new-log-reg__body']//input[@name='password']");

    public By loginButton = By.xpath("//div[@class='new-log-reg__body']//button[@type='submit']");
}
