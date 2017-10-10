package testhomepage;

import base.CommonAPI;
import homepage.SearchFromXL;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSearchFromXL extends CommonAPI{
    @Test(enabled = false)
    public void testSearchFromExcel()throws IOException,InterruptedException {
        SearchFromXL searchFromXL = PageFactory.initElements(driver, SearchFromXL.class);
        searchFromXL.enterDataFromExcelAndSearch();
    }
}
