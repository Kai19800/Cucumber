package Steps;

import io.cucumber.java.en.When;
import utils.CommonMethods;

public class AnotherLoginSteps extends CommonMethods {

    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}