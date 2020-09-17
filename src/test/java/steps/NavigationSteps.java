package steps;

import io.cucumber.java.en.Given;
import core.TestsCore;
import pages.HeaderMenuPage;
import pages.PersonalCabinetMenuPage;

public class NavigationSteps extends TestsCore {

    @Given("I navigate to page {string}")
    public void navigateToMainPage(String url) {
        get(url);
    }

    @Given("I can navigate to about myself information")
    public void navigateToMainPage() {
        clickWithWait(new HeaderMenuPage().myCoursesButton);
        clickWithWait(new PersonalCabinetMenuPage().aboutMySelfButton);
    }
}
