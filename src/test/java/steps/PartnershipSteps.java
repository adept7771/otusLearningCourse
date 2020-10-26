package steps;

import core.TestsCore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.PartnershipPage;

@Execution(ExecutionMode.CONCURRENT)
public class PartnershipSteps extends TestsCore {

    @Given("I can open modal partnership windows")
    public void openModalPartnershipWindow() {
        clickWithWait(new PartnershipPage().teacherRequestButton);
    }

    @Then("I can enter teachers {string} and {string} and {string}")
    public void iCanEnterTeachersFullNameAndPhoneNumber(String fullName, String email, String phone) {
        PartnershipPage partnershipPage = new PartnershipPage();
        sendKeys(partnershipPage.teacherFullNameInput, fullName);
        sendKeys(partnershipPage.teacherEmailInput, email);
        sendKeys(partnershipPage.teacherPhoneInput, phone);
        clickWithWait(partnershipPage.beTeacherButton);
    }

    @Then("I see success modal window")
    public void iSeeSuccessModalWindow() {
        Assert.assertTrue(isElementVisible(new PartnershipPage().successModalWindowOkButton, 5L));
    }

    @Then("I cant see success modal window")
    public void iCantSeeSuccessModalWindow() {
        Assert.assertFalse(isElementVisible(new PartnershipPage().successModalWindowOkButton, 3L));
    }
}
