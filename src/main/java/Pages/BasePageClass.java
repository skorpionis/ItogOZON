package Pages;


import Driver.DriverManager;
import io.qameta.allure.Attachment;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static Driver.DriverManager.getDriver;

/**
 * Created by Ariec on 27.07.2019.
 */
public class BasePageClass {


    WebDriver driver = getDriver();
    JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 15,120);
    public static HashMap<String , Integer> MAP = new HashMap<>();

//skorp9393@mail.ru  573155skorP
    public BasePageClass ()  {
        PageFactory.initElements(driver,this);
    }

   public WebDriverWait getWait(){
       return wait;
   }

    public static Integer getMAP(String key) {
        return MAP.get(key);
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public void checkElement(WebElement element, String t){
        Assert.assertEquals("чек", t,element.getText());
    }
    public void fillTextField(WebElement element, int a){
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(element)).clear();
        element.sendKeys(String.valueOf(a));
    }
    public void fillTextField(WebElement element, String s){
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(element)).clear();
        element.sendKeys(s);
    }
    public WebElement findElement(List<WebElement> arr, String x){
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).getText().equalsIgnoreCase(x)){
                click(arr.get(i));
                return arr.get(i);
            }
        }Assert.fail(String.format("Net elementa na stranice %s ",x));
        return null;
    }
    @Attachment(value = "Error screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }




    public void writeValue(WebElement element, String keys){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

   /* public String getText(WebElement path, int a){
        WebElement element = DriverManager.getDriver().findElement(By.xpath(String.format(path,a)));
        return element.getText();
    }*/

    public WebDriverWait getWebDriverWait() throws IOException {
        if(wait==null)
            wait = new WebDriverWait(getDriver(),15, 300);
        return wait;
    }
    public static void setMAP(String key, Integer value) {
        MAP.put(key, value);
    }
}
