package steps;

import io.cucumber.java.en.Given;
import core.TestsCore;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HeaderMenuPage;
import pages.LessonsPage;
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

    @When("I can navigate to my courses")
    public void iCanNavigateToMyCourses() {
        clickWithWait(new PersonalCabinetMenuPage().myCoursesButton);
    }

    @Then("I can choose second month lessons")
    public void iCanChooseSecondMonthLessons() {
        clickWithWait(new LessonsPage().secondMonthButton);
    }

    @Then("I can choose java qa course")
    public void iCanChooseJavaQaCourse() {
        clickWithWait(new LessonsPage().javaCourseButton);
    }
}
