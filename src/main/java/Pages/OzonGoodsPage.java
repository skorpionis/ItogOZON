package Pages;

import Driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ariec on 27.07.2019.
 */
public class OzonGoodsPage extends BasePageClass {
    @FindBy(xpath = "//*[contains(text(),'Цена')]/parent::div//input[contains(@id,'to')]")
    public WebElement price;
    @FindBy(xpath = "//button[@type='submit']/*")
    public WebElement addToBasket;
     @FindBy(xpath = "//span[@data-test-id='tile-price']")
     public WebElement prodPrice;
     @FindBy(xpath = "//a[@data-test-id='tile-name']")
     public WebElement prodName;
     @FindBy(xpath = "//div[@class='active-filters']//span[contains(text(), 'Цена')]")
     public WebElement cena;
    @FindBy(xpath = "//div[@class='tiles']/*")
    public List<WebElement> allGoodsOnPage;
    @FindBy(xpath = "//span[contains(text(), 'Корзина')]")
    public WebElement basketClick;
    @FindBy(xpath = "//span[@class='show'][1]")
    public WebElement seeAllBrands;
    @FindBy(xpath = "//div[@class='input-wrap search-input m-low-height']/input")
    public WebElement searchBrandField;
    public Map<String,Integer> map = new HashMap<String,Integer>();

    public void chooseMark(Set<String > marks){
        js.executeScript("return arguments[0].scrollIntoView(true);", seeAllBrands);
        click(seeAllBrands);
        String elements = "//div[@class='active-filters']/descendant::span[contains(text(), '%s')]";
        for (String s: marks) {
            click(searchBrandField);
            fillTextField(searchBrandField,s);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(elements, s))));
        }
    }

    public WebElement getBasketClick() {
        return basketClick;
    }

    public List<WebElement> getAllGoodsOnPage() {
        return allGoodsOnPage;
    }

    public WebElement getCena() {
        return cena;
    }

    public WebElement goodsListElem(int i){
        return allGoodsOnPage.get(i);
    }

    public WebElement getSeeAllBrands() {
        return seeAllBrands;
    }

    public WebElement getSearchBrandField() {
        return searchBrandField;
    }

    public WebElement getPrice() {
        return price;
    }

    public WebElement getAddToBasket() {
        return addToBasket;
    }

    public void basketClickk() {
        click(basketClick);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='content']//*[contains(text(), 'Корзина')]")));
    }

    public void jsScroll(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("return arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


}
