package Pages;

import cucumber.api.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ariec on 27.07.2019.
 */
public class OzonBasketPage extends BasePageClass {

    @FindBy(xpath = "//button[contains(text(), 'Удалить')]")
    public WebElement removeCurrent;
    @FindBy(xpath = "//button[contains(text(), 'Выбрать')]")
    public WebElement chooseCurrent;
    @FindBy(xpath = "//div[@data-test-id='total-price-block']/.//span[@class='price-number']")
    public WebElement itogSum;
    @FindBy(xpath = "//button[contains(text(), 'Удалить')][@class='button button blue']")
    public WebElement confirmDelBtn;
    @FindBy(xpath = "//h1[contains(text(),'Корзина пуста')]")
    public WebElement basketIsEmptyText;
    @FindBy(xpath = "//div[@class='cart-list']//*[@data-test-id='cart-item-title']")
    public List<WebElement> list;

    public List<WebElement> getList() {
        return list;
    }

    public WebElement getRemoveCurrent() {
        return removeCurrent;
    }

    public WebElement getChooseCurrent() {
        return chooseCurrent;
    }

    public Integer  getItogSum(){
        Integer a = null;
        if (itogSum.getText().contains(" ")){
           String s1 = itogSum.getText().replaceAll(" ", "");
           a = Integer.parseInt(s1);
        }
        return  a ;
    }

    public WebElement getConfirmDelBtn() {
        return confirmDelBtn;
    }

    public WebElement getBasketIsEmptyText() {
        return basketIsEmptyText;
    }
    @FindBy(xpath = "//a[@data-test-id='cart-item-title']")
    public WebElement itName;
    @FindBy(xpath = "//span[@data-test-id='original-price-of-item-in-cart']")
    public WebElement itPrice;

    public void removeItemsFromBasket(){
        click(removeCurrent);
        click(confirmDelBtn);
    }

    public Map<String, String> cartItems(){
        String itemName;
        String itemPrice;
        if(list.isEmpty()){return null;}

        Map<String,String> map = new HashMap<String,String >();
        for (WebElement element: list) {
            itemName = element.findElement(By.xpath("//a[@data-test-id='cart-item-title']")).getText();
            itemPrice = element.findElement(By.xpath("//span[@data-test-id='original-price-of-item-in-cart']")).getText();
                map.put(itemName,itemPrice);
        }return map;
    }



}
