package steps;

import core.TestsCore;
import dto.User;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import services.UserService;
import utils.Generators;

public class ApiSteps extends TestsCore {

    Response response;
    String generatedUserName;

    @When("I sent post user request with valid data to pet store")
    public void sentCreateUserRequest() {
        UserService userService = new UserService();
        response = userService.addUserRequest(User.builder().email("test").username("testname").lastName("lastname")
        .password("123").phone("22222").id(100L).firstName("FirstName").userStatus(10L).build());
    }

    @When("I sent post user request with data and random name: {string}, {string}, {string}, {string}, {string}")
    public void sentCreateUserRequestWithPredefinedParameters(String firstName, String lastName,
                                                              String email,
                                                              String password, String phone) {
        UserService userService = new UserService();
        generatedUserName = Generators.genString(6, false, true, false,
                false, false);
        response = userService.addUserRequest(User.builder().email(email).username(generatedUserName).lastName(lastName)
                .password(password).phone(phone).id(100L).firstName(firstName).userStatus(10L).build());
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

    @When("I get pre generated user")
    public void getPreGeneratedUser() {
        UserService userService = new UserService();
        response = userService.getUserByName(generatedUserName);
    }

    @Then("Server response code is {int}")
    public void serverResponseCodeIs(int responseCode) {
        Assert.assertEquals(responseCode, response.getStatusCode());
    }

    @Then("User with random name contains data in response: {string}, {string}, {string}, {string}, {string}")
    public void getUserRequestContainsData(String firstName, String lastName, String email,
                                           String password, String phone) {
        User user = response.as(User.class);
        Assert.assertEquals(user.getUsername(), generatedUserName);
        Assert.assertEquals(user.getFirstName(), firstName);
        Assert.assertEquals(user.getLastName(), lastName);
        Assert.assertEquals(user.getEmail(), email);
        Assert.assertEquals(user.getPassword(), password);
        Assert.assertEquals(user.getPhone(), phone);
    }
}
