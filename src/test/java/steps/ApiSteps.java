package steps;

import core.TestsCore;
import dto.User;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.MainPage;
import services.UserService;

public class ApiSteps extends TestsCore {

    Response response;

    @When("I sent post user request with valid data to pet store")
    public void sentCreateUserRequest() {
        UserService userService = new UserService();
        response = userService.addUserRequest(User.builder().email("test").username("testname").lastName("lastname")
        .password("123").phone("22222").id(100L).firstName("FirstName").userStatus(10L).build());
    }

    @When("I sent post user request with valid data to pet store with custom base api path {String}")
    public void sentCreateUserRequest(String apiPath) {
        UserService userService = new UserService();
        response = userService.addUserRequest(User.builder().email("test").username("testname").lastName("lastname")
                .password("123").phone("22222").id(100L).firstName("FirstName").userStatus(10L).build());
    }

    @Then("Server response code is {int}")
    public void serverResponseCodeIs(int responseCode) {
        Assert.assertEquals(responseCode, response.getStatusCode());
    }
}
