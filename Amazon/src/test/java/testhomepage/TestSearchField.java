package testhomepage;

import base.CommonAPI;
import homepage.SearchField;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;


public class TestSearchField extends SearchField {

    @Test(enabled = true)
    public void testSearchItems()throws InterruptedException{
        SearchField searchField = PageFactory.initElements(driver,SearchField.class);
        searchField.searchItems();
    }

    @Test(enabled = false)
    public void testSearchBox()throws InterruptedException{
       SearchField searchField = PageFactory.initElements(driver,SearchField.class);
       searchField.enterInputToSearchField();
   }
    @Test(enabled = false)
    public void testHowToBuy()throws InterruptedException{
        SearchField searchField = PageFactory.initElements(driver,SearchField.class);
        searchField.buyItems();
   }
    @Test(enabled = false)
    public void testSearchDropDownList()throws InterruptedException{
       SearchField searchField = PageFactory.initElements(driver,SearchField.class);
       searchField.clickOnSearchDropDown();
       searchField.selectFromSearchDropDown();
   }

}
