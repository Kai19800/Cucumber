Feature: API workflow resource

  Background:
    Given a JWT is generated

  @api
  Scenario: First Scenario to create an employee
    Given a request is prepared for creating an employee
    When a post call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable

  @api
  Scenario: Retrieving already created employee
    Given a request is prepared to create an employee
    When a get call is made to get the details of the employee
    Then the status code is 200
    And the employee id "employee.employee_id" must match with the global employee id
    And received data from "employee" object matches with the data used to create employee
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
      | Tester        | Updating     | Details         | Female     | 1980-10-02   | inActive   | QA            |

  @json
  Scenario: Creating an employee via json payload method
    Given The request is prepared for creating an employee via json payload
    When a post call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable

  @dynamic
  Scenario: Creating an employee using more dynamic payload
    Given The request is prepared for creating an employee via more dynamic payload "Tester", "Updating", "Details", "F", "1980-10-02", "inActive", "QA"
    When a post call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable






