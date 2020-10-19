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

    @When("I sent post user request with valid data to pet store with custom base api path {string}")
    public void sentCreateUserRequest(String apiPath) {
        UserService userService = new UserService(apiPath);
        response = userService.addUserRequest(User.builder().email("test").username("testname").lastName("lastname")
                .password("123").phone("22222").id(100L).firstName("FirstName").userStatus(10L).build());
    }

    @When("I get user by username: {string}")
    public void getUserByUserName(String username) {
        UserService userService = new UserService();
        response = userService.getUserByName(username);
    }

    @Then("Server response code is {int}")
    public void serverResponseCodeIs(int responseCode) {
        Assert.assertEquals(responseCode, response.getStatusCode());
    }

    @Then("User in response contains data:")
//    public void getUserRequestContainsData(String username, String firstName, String lastName, String email,
//                                           String password, String phone) {

    public void getUserRequestContainsData() {

        User user = response.as(User.class);
//        Assert.assertEquals(user.getUsername(), username);
//        Assert.assertEquals(user.getFirstName(), firstName);
//        Assert.assertEquals(user.getLastName(), lastName);
//        Assert.assertEquals(user.getEmail(), email);
//        Assert.assertEquals(user.getUsername(), username);
//        Assert.assertEquals(user.getUsername(), username);
//        Assert.assertEquals(user.getUsername(), username);
//        Assert.assertEquals(user.getUsername(), username);

        System.out.println("test");
    }
}
