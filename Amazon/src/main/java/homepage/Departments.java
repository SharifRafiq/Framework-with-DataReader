package homepage;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Departments extends CommonAPI {

    Actions actions = new Actions(driver);

    @FindBy(how = How.XPATH, using = ".//*[@id='nav-link-shopall']/span[2]")
    WebElement department;
    @FindBy(how = How.XPATH, using = ".//*[@id='a-page']/div[2]/div/div[3]/div[1]/h2")
    WebElement homeGardensAndTools;
    @FindBy(how = How.XPATH, using = ".//*[@id='a-page']/div[2]/div/div[3]/div[1]/div/a[2]")
    WebElement kitchenAndDining;
    @FindBy(how = How.XPATH, using = ".//div[1]/header/div/div[2]/div[1]/div/a/span[2]")
    WebElement deptDropDownBtn;
    @FindBy(how = How.XPATH, using = ".//*[@id='nav-flyout-shopAll']/div[2]/span[1]/span")
    WebElement amzonVideo;
    @FindBy(how = How.XPATH,using = ".//*[@id='nav-flyout-shopAll']/div[3]/div[1]/div[1]/div/a[1]/span[1]"  )
    WebElement allVideo;

    public void clickOnDepartment(){
        department.click();
        homeGardensAndTools.click();
        kitchenAndDining.click();
    }
    public void mouseHoverDepartments()throws InterruptedException{
        actions.moveToElement(deptDropDownBtn).perform();
        actions.moveToElement(amzonVideo).perform();
        //actions.moveToElement(allVideo).click().perform();
        allVideo.click();
    }
}