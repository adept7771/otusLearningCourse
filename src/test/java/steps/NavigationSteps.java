package steps;

import io.cucumber.java.en.Given;
import core.TestsCore;

public class NavigationSteps extends TestsCore {

    @Given("I navigate to page {string}")
    public void navigateToMainPage(String url) {
        get(url);
    }

}
