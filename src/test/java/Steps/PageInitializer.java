package Steps;

import Pages.AddEmployeePage;
import Pages.DashboardPage;
import Pages.EmployeeInformationPage;
import Pages.LoginPage;

public class PageInitializer {

    public static LoginPage login;
    public static DashboardPage dash;
    public static EmployeeInformationPage emp;
    public static AddEmployeePage addEmployeePage;

    public static void initializePageObjects(){
        login = new LoginPage();
        dash = new DashboardPage();
        emp = new EmployeeInformationPage();
        addEmployeePage = new AddEmployeePage();
    }


}
