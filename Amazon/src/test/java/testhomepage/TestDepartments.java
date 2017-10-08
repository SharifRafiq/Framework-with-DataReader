package testhomepage;

import base.CommonAPI;
import homepage.Departments;
import org.openqa.selenium.support.PageFactory;

public class TestDepartments extends CommonAPI {

    public void testDepartment(){
        Departments dept =PageFactory.initElements(driver,Departments.class);
        dept.clickOnDepartment();
    }
    public void testMouseHoverDepartments()throws InterruptedException{
        Departments dept =PageFactory.initElements(driver,Departments.class);
        dept.mouseHoverDepartments();
    }
}
