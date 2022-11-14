package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //Let's perform CRUD operations
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjYxODcyMjYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY2NjIzMDQyNiwidXNlcklkIjoiNDU4OCJ9.H1ulalb7gv6D8Tdoss5rz_ChV1_-k0f6Y91hYjcSklY";
    static String employee_id;

    //create a request for creation of an employee
    @Test
    public void a_createAnEmployee() {

        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Suzan\",\n" +
                        "  \"emp_lastname\": \"Gibson\",\n" +
                        "  \"emp_middle_name\": \"Steve\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1980-10-09\",\n" +
                        "  \"emp_status\": \"Active\",\n" +
                        "  \"emp_job_title\": \"Accountant\"\n" +
                        "}");
        //send the request to the server and get the response back
        Response response = request.when().post("/createEmployee.php");

        //print the response in console
        response.prettyPrint();

        //This is how we verify status code
        response.then().assertThat().statusCode(201);
        //This is how we verify data from the response body
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("Steve"));
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Suzan"));
        response.then().assertThat().body("Employee.emp_birthday", equalTo("1980-10-09"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        //This is how we verify headers
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

        //This is how we search for an employee
        //first create static String employee_id; above under the public class
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }

    @Test
    public void b_getCreatedEmployee() {
        RequestSpecification GetRequest =
                given().header("Content-Type", "application/json").
                        header("Authorization", token).
                        queryParam("employee_id", employee_id);
        Response response = GetRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void c_updatedEmployee() {
        RequestSpecification updateRequest =
                given().header("Authorization", token).header("Content-Type", "application/json").body
                        ("{\n" +
                                "        \"employee_id\": \"" + employee_id + "\",\n" +
                                "        \"emp_firstname\": \"Suzan\",\n" +
                                "        \"emp_middle_name\": \"Steve\",\n" +
                                "        \"emp_lastname\": \"kk\",\n" +
                                "        \"emp_birthday\": \"1980-10-09\",\n" +
                                "        \"emp_gender\": \"M\",\n" +
                                "        \"emp_job_title\": \"Accountant\",\n" +
                                "        \"emp_status\": \"Active\"\n" +
                                "    }");
        Response response = updateRequest.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void d_getUpdatedEmployee() {
        RequestSpecification GetUpdatedEmployeeRequest =
                given().header("Content-Type", "application/json").
                        header("Authorization", token).
                        queryParam("employee_id", employee_id);
        Response response = GetUpdatedEmployeeRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Employee.emp_lastname", equalTo("kk"));
    }
}