package steps;

import io.cucumber.java.en.Given;
import core.TestsCore;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.*;

@Execution(ExecutionMode.CONCURRENT)
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

    @Then("I can navigate to payment page")
    public void iCanNavigateToPaymentPage() {
        clickWithWait(new PersonalCabinetMenuPage().payButton);
    }

    @When("I can navigate to partnership page")
    public void iCanNavigateToPartnershipPage() {
        clickWithWait(new FooterMenuPage().beATeacherButton);
    }

    @When("I navigate to qa java course")
    public void iNavigateToQaJavaCourse() {
        MainPage mainPage = new MainPage();
        clickWithWait(mainPage.testingCoursesLink);
        //scrollToElement(mainPage.gameTestingCourseLink);
        scrollToElement(mainPage.qaJavaCourseLink);
        clickWithWait(mainPage.qaJavaCourseLink);
    }

    @Then("i should see the best teacher at course")
    public void iShouldSeeTheBestTeacherAtCourse() {
        Assert.assertTrue(isElementVisible(new MainPage().bestTeacherAtCourse, 10L));
    }
}
