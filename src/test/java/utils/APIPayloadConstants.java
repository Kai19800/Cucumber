package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload() {
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Tester\",\n" +
                "  \"emp_lastname\": \"Updating\",\n" +
                "  \"emp_middle_name\": \"Details\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1980-10-02\",\n" +
                "  \"emp_status\": \"inActive\",\n" +
                "  \"emp_job_title\": \"QA\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeePayloadJSON() {
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Tester");
        obj.put("emp_lastname", "Updating");
        obj.put("emp_middle_name", "Details");
        obj.put("emp_gender", "F");
        obj.put("emp_birthday", "1980-10-02");
        obj.put("emp_status", "inActive");
        obj.put("emp_job_title", "QA");

        return obj.toString();
    }

    public static String createEmployeeDynamicPayload(String firstName, String lastName, String middleName,
                                                      String gender, String birthday,
                                                      String empStatus, String jobTitle) {
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", firstName);
        obj.put("emp_lastname", lastName);
        obj.put("emp_middle_name", middleName);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", birthday);
        obj.put("emp_status", empStatus);
        obj.put("emp_job_title", jobTitle);

        return obj.toString();
    }
}