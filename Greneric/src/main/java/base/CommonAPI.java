package base;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonAPI {
   public static WebDriver driver = null;
   private String saucelabs_username = "";
   private String browserstack_username = "";
   private String saucelabs_accesskey = "";
   private String browserstack_accesskey = "";

   @Parameters({"useCloudEnv","cloudEnvName","os","os_version","browserName","browserName","url"})
   @BeforeMethod
   public void setUp(boolean useCloudEnv, String cloudEnvName, String os, String os_version,
                     String browserName, String browserVersion, String url) throws IOException{
      if (useCloudEnv == true) {
         if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
            getCloudDriver(cloudEnvName, saucelabs_username, saucelabs_accesskey, os, os_version, browserName, browserVersion);
         } else if (cloudEnvName.equalsIgnoreCase("browserstack")) {
            getCloudDriver(cloudEnvName, browserstack_username, browserstack_accesskey, os, os_version, browserName, browserVersion);
         }
      } else {
         getLocalDriver(browserName, os);
      }
      driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
      driver.manage().window().maximize();
      driver.navigate().to(url);
   }
   public WebDriver getLocalDriver(String browserName, String os) {
      if (browserName.equalsIgnoreCase("chrome")) {
         if (os.equalsIgnoreCase("os x")) {
            System.setProperty("webdriver.chrome.driver", "..\\Greneric/driver/chromedriver");
         } else if (os.equalsIgnoreCase("windows")) {
            System.setProperty("webdriver.chrome.driver", "C:..\\Greneric\\driver\\chromedriver.exe");
         }
         driver = new ChromeDriver();
      }
      else if (browserName.equalsIgnoreCase("firefox")) {
         if (os.equalsIgnoreCase("os x")) {
            System.setProperty("webdriver.gecko.driver", "..\\Greneric\\driver\\geckodriver");
         } else if (os.equalsIgnoreCase("windows")) {
            System.setProperty("webdriver.gecko.driver", "..\\Greneric\\driver\\geckodriver.exe");
         }
         driver = new FirefoxDriver();
      }else if (browserName.equalsIgnoreCase("ie")) {
         System.setProperty("webdriver.ie.driver", "C:..\\Greneric\\driver\\IEDriverServer.exe");
         driver = new InternetExplorerDriver();
      }

      return driver;
   }

   public WebDriver getCloudDriver(String cloudEnvName,String username, String accesskey, String os, String os_version, String browserName, String browserVersion)throws IOException{
      DesiredCapabilities cap = new DesiredCapabilities();
      cap.setCapability("browserName", browserName);
      cap.setCapability("browserVersion", browserVersion);
      cap.setCapability("os",os);
      cap.setCapability("os_version",os_version);

      if(cloudEnvName.equalsIgnoreCase("saucelabs")){
         driver = new RemoteWebDriver(new URL("http://" + username + ":" + accesskey + "@ondemand.saucelabs.com:80/wd/hub"),cap);
      }else if(cloudEnvName.equalsIgnoreCase("browserstack")){
         cap.setCapability("resolution", "1024x768");
         driver = new RemoteWebDriver(new URL("http://" + username + ":" + accesskey + "@hub-cloud.browserstack.com/wd/hub"),cap);
      }

      return driver;
   }
   @AfterMethod
   public  static void cleanUp(){
      driver.close(); }
   public void clickByCss(String locator){
      driver.findElement(By.cssSelector(locator)).click();
   }
   public void clickByXpath(String locator){
      driver.findElement(By.xpath(locator)).click();
   }
   public void typeByCss(String locator, String value){
      driver.findElement(By.cssSelector(locator)).sendKeys(value);
   }
   public void typeByCssAndEnter(String locator, String value){
      driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER); }
   public void typeByXpath(String locator, String value){ driver.findElement(By.xpath(locator)).sendKeys(value); }
   public void useEnterKeys(String locator){driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);}
   public static void clearField(String locator){
      driver.findElement(By.cssSelector(locator)).clear();
   }

   public List<WebElement> getListOfWebElementsById(String locator) {
      List<WebElement> list = new ArrayList<WebElement>();
      list = driver.findElements(By.id(locator));
      return list; }
   public List<String> getTextFromWebElements(String locator){
      List<WebElement> element = new ArrayList<WebElement>();
      List<String> text = new ArrayList<String>();
      element = driver.findElements(By.cssSelector(locator));
      for(WebElement web:element){
         text.add(web.getText());
      }
      return text;
   }
   public List<String> getTextFromWebElementsXpath(String locator){
      List<WebElement> element = new ArrayList<WebElement>();
      List<String> text = new ArrayList<String>();
      element = driver.findElements(By.xpath(locator));
      for(WebElement web:element){
         text.add(web.getText());
      }
      return text;
   }
   public List<WebElement> getListOfWebElementsByCss(String locator) {
      List<WebElement> list = new ArrayList<WebElement>();
      list = driver.findElements(By.cssSelector(locator));
      return list;
   }
   public List<WebElement> getListOfWebElementsByXpath(String locator) {
      List<WebElement> list = new ArrayList<WebElement>();
      list = driver.findElements(By.xpath(locator));
      return list;
   }
   public String  getCurrentPageUrl(){
      String url = driver.getCurrentUrl();
      return url;
   }
   public void navigateBack(){
      driver.navigate().back();
   }
   public void navigateForward(){
      driver.navigate().forward();
   }
   public String getTextByCss(String locator){
      String st = driver.findElement(By.cssSelector(locator)).getText();
      return st;
   }
   public String getTextByXpath(String locator){
      String st = driver.findElement(By.xpath(locator)).getText();
      return st;
   }
   public String getTextById(String locator){
      return driver.findElement(By.id(locator)).getText();
   }
   public String getTextByName(String locator){
      String st = driver.findElement(By.name(locator)).getText();
      return st;
   }
   public List<String> getListOfString(List<WebElement> list) {
      List<String> items = new ArrayList<String>();
      for (WebElement element : list) {
         items.add(element.getText());
      }
      return items;
   }
   public void selectOptionByVisibleText(WebElement element, String value) {
      Select select = new Select(element);
      select.selectByVisibleText(value);
   }
   public void sleepFor(int sec)throws InterruptedException{
      Thread.sleep(sec * 1000);
   }
   public void mouseHoverByCSS(String locator){
      try {
         WebElement element = driver.findElement(By.cssSelector(locator));
         Actions action = new Actions(driver);
         Actions hover = action.moveToElement(element);
      }catch(Exception ex){
         System.out.println("First attempt has been done, This is second try");
         WebElement element = driver.findElement(By.cssSelector(locator));
         Actions action = new Actions(driver);
         action.moveToElement(element).perform();
      }
   }
   public void mouseHoverByXpath(String locator){
      try {
         WebElement element = driver.findElement(By.xpath(locator));
         Actions action = new Actions(driver);
         Actions hover = action.moveToElement(element);
      }catch(Exception ex){
         System.out.println("First attempt has been done, This is second try");
         WebElement element = driver.findElement(By.cssSelector(locator));
         Actions action = new Actions(driver);
         action.moveToElement(element).perform();

      }
   }

   public void mouseHoverByLinkText(String linkText){
      try {
         WebElement element = driver.findElement(By.linkText(linkText));
         Actions action = new Actions(driver);
         Actions hover = action.moveToElement(element);
      }catch(Exception ex){
         System.out.println("First attempt has been done, This is second try");
         WebElement element = driver.findElement(By.linkText(linkText));
         Actions action = new Actions(driver);
         action.moveToElement(element).perform();
      }
   }
   //handling Alert
   public void okAlert(){
      Alert alert = driver.switchTo().alert();
      alert.accept();
   }
   public void cancelAlert(){
      Alert alert = driver.switchTo().alert();
      alert.dismiss();
   }

   //iFrame Handle
   public void iframeHandle(WebElement element){
      driver.switchTo().frame(element);
   }

   public void goBackToHomeWindow(){
      driver.switchTo().defaultContent();
   }

   //get Links
   public void getLinks(String locator){
      driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
   }

   public static void captureScreenshot(WebDriver driver, String screenshotName){

      DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
      Date date = new Date();
      df.format(date);

      File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      try {
         FileUtils.copyFile(file, new File(System.getProperty("user.dir")+ "/screenshots/"+screenshotName+" "+df.format(date)+".png"));
         System.out.println("Screenshot captured");
      } catch (Exception e) {
         System.out.println("Exception while taking screenshot "+e.getMessage());;
      }

   }
   //Taking Screen shots
   public void takeScreenShot()throws IOException {
      File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(file,new File("screenShots.png"));
   }
   //Synchronization
   public void waitUntilClickAble(By locator){
      WebDriverWait wait = new WebDriverWait(driver, 10);
      WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
   }
   public void waitUntilVisible(By locator){
      WebDriverWait wait = new WebDriverWait(driver, 10);
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
   }
   public void waitUntilSelectable(By locator){
      WebDriverWait wait = new WebDriverWait(driver, 10);
      boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
   }
   public void upLoadFile(String locator,String path){
      driver.findElement(By.cssSelector(locator)).sendKeys(path);
        /* path example to upload a file/image
           path= "C:\\Users\\rrt\\Pictures\\ds1.png";
         */
   }
   public void clearInput(String locator){
      driver.findElement(By.cssSelector(locator)).clear();
   }
   public void keysInput(String locator){
      driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
   }
   public String converToString(String st){
      String splitString ;
      splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
      return splitString;
   }

   public WebElement getElement(String type, String locator) {
      type = type.toLowerCase();
      if (type.equals("id")) {
         System.out.println("Element found with id: " + locator);
         return driver.findElement(By.id(locator));
      } else if (type.equals("name")) {
         System.out.println("Element found with name: " + locator);
         return driver.findElement(By.name(locator));
      } else if (type.equals("xpath")) {
         System.out.println("Element found with xpath: " + locator);
         return driver.findElement(By.xpath(locator));
      } else if (type.equals("css")) {
         System.out.println("Element found with css: " + locator);
         return driver.findElement(By.cssSelector(locator));
      } else if (type.equals("classname")) {
         System.out.println("Element found with classname: " + locator);
         return driver.findElement(By.className(locator));
      } else if (type.equals("tagname")) {
         System.out.println("Element found with tagname: " + locator);
         return driver.findElement(By.tagName(locator));
      } else if (type.equals("linktext")) {
         System.out.println("Element found with link text: " + locator);
         return driver.findElement(By.linkText(locator));
      } else if (type.equals("partiallinktext")) {
         System.out.println("Element found with partial link text: " + locator);
         return driver.findElement(By.partialLinkText(locator));
      } else {
         System.out.println("Locator type not supported");
         return null;
      }

   }

   public List<WebElement> getElementList(String type, String locator) {
      type = type.toLowerCase();
      List<WebElement> elementList = new ArrayList<WebElement>();

      if (type.equals("id")) {
         elementList = driver.findElements(By.id(locator));
      } else if (type.equals("name")) {
         elementList = driver.findElements(By.name(locator));
      } else if (type.equals("xpath")) {
         elementList = driver.findElements(By.xpath(locator));
      } else if (type.equals("css")) {
         elementList = driver.findElements(By.cssSelector(locator));
      } else if (type.equals("classname")) {
         elementList = driver.findElements(By.className(locator));
      } else if (type.equals("tagname")) {
         elementList = driver.findElements(By.tagName(locator));
      } else if (type.equals("linktext")) {
         elementList = driver.findElements(By.linkText(locator));
      } else if (type.equals("partiallinktext")) {
         elementList = driver.findElements(By.partialLinkText(locator));
      } else {
         System.out.println("Locator type not supported");
      }
      if (elementList.isEmpty()) {
         System.out.println("Element not found with " + type + ": " + locator);

      } else {
         System.out.println("Element found with " + type + ": " + locator);
      }
      return elementList;
   }

   public boolean isElementPresent(String type, String locator) {
      List<WebElement> elementList = getElementList(type, locator);
      int size = elementList.size();
      if (size > 0) {
         return true;
      } else {
         return false;
      }
   }

   public List<String> getTextFromWebElements(String type, String locator) {
      List<WebElement> element;
      List<String> text = new ArrayList<>();
      element = getElementList(type, locator);
      for (WebElement we : element) {
         text.add(we.getText());
      }
      return text;
   }

   public WebElement waitForElement(By locator, int timeout) {
      WebElement element = null;
      try {
         System.out.println("Waiting for a max: " + timeout + " seconds for element to be available.");
         WebDriverWait wait = new WebDriverWait(driver, timeout);
         element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
         System.out.println("Element found on web page");
      } catch (Exception e) {
         System.err.println("Element not found on web page.");
      }
      return element;
   }

   public void clickWhenReady(By locator, int timeout) {
      try {
         WebElement element = null;
         System.out.println("Waiting for a max: " + timeout + " seconds for element to be clickable.");
         WebDriverWait wait = new WebDriverWait(driver, timeout);
         element = wait.until(ExpectedConditions.elementToBeClickable(locator));
         element.click();
         System.out.println("Element clicked on web page");
      } catch (Exception e) {
         System.err.println("Element not found on web page.");
      }
   }

   public String getLinkStatus(URL url) {
      try {
         HttpURLConnection http = (HttpURLConnection) url.openConnection();
         http.connect();
         String responseMessage = http.getResponseMessage();
         http.disconnect();
         return responseMessage;
      } catch (Exception e) {
         return e.getMessage();
      }
   }

   public void getTestResult(ITestResult testResult){
      if(testResult.getStatus() == ITestResult.FAILURE){
         System.out.println("FAILED: " + testResult.getName());
      }else if(testResult.getStatus() == ITestResult.SUCCESS){
         System.out.println("SUCCESSFUL: " + testResult.getName());
      }
   }

   public void checkTitle(String actualTitle){
      String expectedTitle = driver.getTitle();
      Assert.assertEquals(expectedTitle,actualTitle);
      System.out.println(expectedTitle);
   }
}

