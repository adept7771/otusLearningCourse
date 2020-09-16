package steps;

import core.TestCore;
import io.cucumber.java.en.Given;

public class NavigationSteps extends TestCore {

    @Given("I navigate to page {string}")
    public void navigateToMainPage(String url) {
        getWebDriver().navigate().to(url);
    }

}
