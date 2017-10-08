package homepage;

import Utility.DataReader;
import base.CommonAPI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class SearchFromXL extends CommonAPI {
    DataReader dr = new DataReader();
    public String [] searchDataFromExcel() throws IOException{
        String path = System.getProperty("user.dir") + "/Data/itemsToSearch.xls";
        String [] st = dr.fileReader(path);
        return st;
    }
    @FindBy(how = How.CSS, using = "#twotabsearchtextbox")
    WebElement searchBoxInput;
    @FindBy(how = How.CLASS_NAME, using = "nav-input")
    WebElement searchButton;


    public  void searchFor(String value){
        searchBoxInput.sendKeys(value, Keys.ENTER);
    }
    public void clickOnSearch(){
        searchButton.click();
    }

    public void clearSearchInput(){
        searchBoxInput.clear();
    }
    public  void enterDataFromExcelAndSearch()throws IOException,InterruptedException{
        SearchFromXL search = new SearchFromXL();
        SearchFromXL searchFromXL = PageFactory.initElements(driver,SearchFromXL.class);
        String [] value =  search.searchDataFromExcel();
        for(int i=1; i<value.length; i++){
            searchFromXL.searchFor(value[i]);
            Thread.sleep(5000);
            searchFromXL.clearSearchInput();

        }

    }
}
