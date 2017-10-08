package homepage;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchField  extends CommonAPI {

    @FindBy(how=How.ID, using = "twotabsearchtextbox")
    WebElement searchBoxInput;
    @FindBy(how = How.CLASS_NAME, using = "nav-input")
    WebElement searchButton;
    @FindBy(how = How.ID, using = "twotabsearchtextbox")
    WebElement clearSearchBox;
    @FindBy(how = How.XPATH, using = ".//*[@id='result_0']/div/div/div/div[2]/div[1]/div[1]/a/h2")
    WebElement title;
    @FindBy(how = How.XPATH, using = ".//*[@id='mediaOlp']/div/div/div/div[1]/div[2]/div/span[1]/a")
    WebElement noOfNewItems;
    @FindBy(how = How.XPATH, using = ".//*[@id='a-autoid-0']/span/input")
    WebElement addToCart;
    @FindBy(how = How.XPATH, using = ".//*[@id='hlb-ptc-btn-native']")
    WebElement proceedToCheckout;
    @FindBy(how = How.ID, using = "ap_email")
    WebElement signInEmail;
    @FindBy(how = How.ID, using = "ap_password")
    WebElement signInPassword;
    @FindBy(how = How.ID, using = "signInSubmit")
    WebElement signInButton;
    @FindBy(how = How.ID, using = "searchDropdownBox")
    WebElement searchDropDown;

    public  void enterInputToSearchField()throws InterruptedException{
        String value = null;
        searchBoxInput.sendKeys("978-1118230725");
        searchButton.click();
        clearSearchBox.clear();
        searchBoxInput.sendKeys("978-1259667473");
        searchButton.click();
    }
    public  void buyItems()throws InterruptedException{
        String value = "978-1259667473";
        searchBoxInput.sendKeys(value);
        searchButton.click();
        title.click();
        //((JavascriptExecutor) driver).executeScript("scroll(0,300)");
        noOfNewItems.click();
        addToCart.click();
        proceedToCheckout.click();
        signInEmail.sendKeys("sharif@gmail.com");
        signInPassword.sendKeys("123568");
        signInButton.click();

    }
    public void clickOnSearchDropDown(){

        searchDropDown.click();
    }
    public void selectFromSearchDropDown()throws InterruptedException{
        Select select = new Select(searchDropDown);
        System.out.println("Select Alexa Skills by index");
        select.selectByIndex(1);
        System.out.println("Select Amazon Video by value");
        select.selectByValue("search-alias=instant-video");
        System.out.println("Select Books by visible text");
        select.selectByVisibleText("Books");
        List<WebElement> list = select.getOptions();
        for(int i = 0; i<list.size();i++){
            String listOfDropDowns = list.get(i).getText();
            select.selectByIndex(i);
            System.out.println(listOfDropDowns);

        }
    }

}
