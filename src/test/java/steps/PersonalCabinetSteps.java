package steps;

import core.TestsCore;
import io.cucumber.java.en.Then;
import pages.LoginPage;

public class PersonalCabinetSteps extends TestsCore {

    @Then("I can login with correct login and password")
    public void PersonalCabinetSteps() {
        LoginPage loginPage = new LoginPage();
        sendKeys(loginPage.emailInput, "dmitry.potapov@btsdigital.kz");
        sendKeys(loginPage.passwordInput, "NAd1R7IyOiFc");
        clickWithWait(loginPage.loginButton);
    }
}
