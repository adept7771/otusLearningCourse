package pages;

import core.TestsCore;
import org.openqa.selenium.By;

public class LoginPage extends TestsCore {

    public By emailInput = By.xpath("//div[@class='new-log-reg__body']" +
            "//input[@name='email' and @placeholder='Электронная почта']");
    public By passwordInput = By.xpath("//div[@class='new-log-reg__body']//input[@name='password']");
    public By loginButton = By.xpath("//div[@class='new-log-reg__body']//button[@type='submit']");
    public By authorisationError = By.xpath("//div[@class='new-input-error new-input-error_top new-input-error_form js-text']");
}
