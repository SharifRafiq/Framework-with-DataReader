package testhomepage;

import base.CommonAPI;
import homepage.Departments;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class TestDepartments extends CommonAPI {

    @Test(enabled = false)
    public void testDepartment(){
        Departments dept =PageFactory.initElements(driver,Departments.class);
        dept.clickOnDepartment();
    }
    @Test(enabled = false)
    public void testMouseHoverDepartments()throws InterruptedException{
        Departments dept =PageFactory.initElements(driver,Departments.class);
        dept.mouseHoverDepartments();
    }
}
