package APIsteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static APIsteps.GenerateTokenSteps.token;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIworkFlowSteps {

    RequestSpecification request;
    Response response;
    public static String employee_id;

    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {

        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a post call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);

    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);

    }

    @Then("the employee created contains key {string} and value {string}")
    public void the_created_employee_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee id {string} is stored as a global variable")
    public void the_employee_id_is_stored_as_a_global_variable(String empID) {
        employee_id = response.jsonPath().getString(empID);
        System.out.println(employee_id);
    }

    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {

        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, token).
                queryParam("employee_id", employee_id);

    }

    @When("a get call is made to get the details of the employee")
    public void a_get_call_is_made_to_get_the_details_of_the_employee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code is {int}")
    public void the_status_code_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee id {string} must match with the global employee id")
    public void the_employee_id_must_match_with_the_global_employee_id(String employeeID) {
        String Temp_employee_id = response.jsonPath().getString(employeeID);
        Assert.assertEquals(Temp_employee_id, employeeID);
    }

    @Then("received data from {string} object matches with the data used to create employee")
    public void received_data_from_object_matches_with_the_data_used_to_create_employee(String empObject, io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> expectedData = dataTable.asMaps();
        //where is my dataTable? It's in the feature file
        Map<String, String> actualData = response.body().jsonPath().get(empObject);
        //jasonPath will retrieve the whole body of the actual response
        //empObject = employee object = the whole response body
        //postman response = one map only, but where's the list of map?
        //The list of map is available in the feature file
        //so, we need to compare the actual map available in the postman response (which is inside the object)
        //with the list of map we created in the feature file
        for (Map<String, String> map : expectedData

        ) {
            //How to get all the keys? We use a Set
            Set<String> keys = map.keySet();
            //Why set not list? Because a set doesn't allow duplicate values
            //This set will get all the keys, but how to get the keys one by one?
            //We need to use another for loop to get a single key in every loop
            for (String singleKey : keys) {
                //expectedValue will get the value for each key from the feature file
                String expectedValue = map.get(singleKey);
                //actualValue will get the value for each key from the response
                String actualValue = actualData.get(singleKey);
                Assert.assertEquals(expectedValue, actualValue);
            }
        }


    }

    @Given("The request is prepared for creating an employee via json payload")
    public void the_request_is_prepared_for_creating_an_employee_via_json_payload() {

        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, token).
                body(APIPayloadConstants.createEmployeePayloadJSON());
    }

    @Given("The request is prepared for creating an employee via more dynamic payload {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void the_request_is_prepared_for_creating_an_employee_via_more_dynamic_payload
            (String firstName, String lastName, String middleName,
             String gender, String birthday,
             String empStatus, String jobTitle) {

        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, token).
                body(APIPayloadConstants.
                        createEmployeeDynamicPayload(firstName, lastName,
                                middleName, gender, birthday, empStatus, jobTitle));
    }
}
