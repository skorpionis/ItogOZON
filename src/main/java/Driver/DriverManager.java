package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/**
 * Created by Ariec on 27.07.2019.
 */
public class DriverManager {
    public static WebDriver driver;
    public static final Properties properties = new Properties();

    @Before
    public static void StartDriver() throws IOException {
        properties.load(new FileInputStream(new File("src/main/resources/driver.properties")));
        switch (properties.getProperty("browser")){
            case("chrome"):
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver  = new ChromeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver  = new ChromeDriver();
        }
        driver.get(properties.getProperty("url"));
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        }

        public static void CloseDriver(){
            for (String s:driver.getWindowHandles()) {
                driver.switchTo().window(s).close();
                driver.quit();
            }
        }

    public static WebDriver getDriver()  {
        if(driver == null) {
            try {
                StartDriver();
            } catch (IOException e) {
                e.printStackTrace();
            }
            driver.get(properties.getProperty("url"));
        }
        return driver;
    }

    public static void changePage(WebElement element) throws IOException {
        Set<String> oldElem = getDriver().getWindowHandles();
        element.click();
        Set<String> newElem = getDriver().getWindowHandles();
        newElem.removeAll(oldElem);
        getDriver().switchTo().window(newElem.iterator().next());
        getDriver().manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
    }


    public static Properties getProperties() {
        return properties;
    }

    public static WebDriver getDriver(String s )  {
        if(driver == null) {

            try {
                StartDriver();
            } catch (IOException e) {
                e.printStackTrace();
            }
            driver.get(s);
        }
        return driver;
    }
}

