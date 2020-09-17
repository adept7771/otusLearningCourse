package steps;

import core.TestsCore;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.HeaderMenuPage;
import pages.LoginPage;
import pages.PrivateDataPage;

public class PersonalCabinetSteps extends TestsCore {

    @Then("I can login with correct login and password")
    public void personalCabinetSteps() {
        LoginPage loginPage = new LoginPage();
        clickWithWait(new HeaderMenuPage().loginButton);
        sendKeys(loginPage.emailInput, "dmitry.potapov@btsdigital.kz");
        sendKeys(loginPage.passwordInput, "NAd1R7IyOiFc");
        clickWithWait(loginPage.loginButton);
    }

    @Then("I can change username to {string}")
    public void changeUsername(String username) {
        sendKeys(new PrivateDataPage().nameInput, username);
    }

    @Then("I can change latinUserName to {string}")
    public void changeLatinUserName(String username) {
        sendKeys(new PrivateDataPage().nameLatinInput, username);
    }

    @Then("I save new personal data")
    public void savePersonalData() {
        clickWithWait(new PrivateDataPage().saveDataButton);
    }


    @Then("I check personal data of {string} and {string} at personal data page")
    public void checkPersonalData(String username, String latinUserName) {
        PrivateDataPage privateDataPage = new PrivateDataPage();
        Assert.assertEquals(getText(privateDataPage.nameInput), username);
        Assert.assertEquals(getText(privateDataPage.nameLatinInput), latinUserName);
    }
}
