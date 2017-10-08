package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CommonAPI {
   public static WebDriver driver = null;
   private String saucelabs_username = "";
   private String browserstack_username = "";
   private String saucelabs_accesskey = "";
   private String browserstack_accesskey = "";

   @Parameters({"useCloudEnv","cloudEnvName","os","os_version","browserName","browserName","url"})
   //Insert  optional to all params.
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
      driver.manage().window().maximize();
      driver.navigate().to(url);
   }
   //insert@optional os mac
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
   public  void cleanUp(){

      driver.close();
   }
   public  void typeByCss(String locator, String value){
      driver.findElement(By.cssSelector(locator)).sendKeys(value);
   }
   public static void clickByCss(String locator){

      driver.findElement(By.cssSelector(locator)).click();
   }
   public static void clearField(String locator){

      driver.findElement(By.cssSelector(locator)).clear();
   }


}

