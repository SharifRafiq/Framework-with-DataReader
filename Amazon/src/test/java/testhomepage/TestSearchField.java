package testhomepage;

import base.CommonAPI;
import homepage.SearchField;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;


public class TestSearchField extends SearchField {

   public void testSearchBox()throws InterruptedException{
       SearchField searchField = PageFactory.initElements(driver,SearchField.class);
       searchField.enterInputToSearchField();
   }
    public void testHowToBuy()throws InterruptedException{
        SearchField searchField = PageFactory.initElements(driver,SearchField.class);
        searchField.buyItems();
   }
    public void testSearchDropDownList()throws InterruptedException{
       SearchField searchField = PageFactory.initElements(driver,SearchField.class);
       searchField.clickOnSearchDropDown();
       searchField.selectFromSearchDropDown();
   }

}
