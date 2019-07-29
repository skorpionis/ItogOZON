package Steps;

import Driver.DriverManager;
import Pages.BasePageClass;
import Pages.OzonBasketPage;
import Pages.OzonGoodsPage;
import Pages.OzonMainPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.ru.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.*;

import static Pages.BasePageClass.setMAP;
/**
 * Created by Ariec on 27.07.2019.
 */
public class CucumberSteps  {
    private OzonBasketPage ozonBasketPage = new OzonBasketPage();
    private OzonGoodsPage ozonGoodsPage = new OzonGoodsPage();
    private OzonMainPage ozonMainPage = new OzonMainPage();
    private StringBuilder stringB = new StringBuilder();





    @After
    public void close(Scenario scenario) {
        if (scenario.isFailed()) {
            ozonMainPage.takeScreenshot();
            ozonGoodsPage.basketClickk();
            String s1 = ozonGoodsPage.getBasketStr().getText();
            if (!s1.equals("Корзина пуста")) {
                cleaningBasket();
            }
        }
        DriverManager.CloseDriver();
    }

    @Дано("Переход по ссылке {string}")
    public void startTesting(String URL){
        DriverManager.getDriver(URL);
    }

    @Тогда("Логинимся с логином {string} и паролем {string}")
    public void loginOzon(String l, String p) throws InterruptedException {
        ozonMainPage.enter();
        ozonMainPage.withEmail();
        ozonMainPage.email(l);
        ozonMainPage.password(p);
        ozonMainPage.submit();
        Thread.sleep(4000);
    }

    @Когда("Ищем с параметром {string}")
    public void searchingWithParam(String s) throws InterruptedException {
        if(!s.equals(ozonMainPage.getSearchingField().getAttribute("value"))){
            ozonMainPage.fillTextField(ozonMainPage.getSearchingField(),s);
            ozonMainPage.click(ozonMainPage.getSearchBtn());
        }else {
            return;
        }
        Thread.sleep(4000);
    }


    @Тогда("Устанавливаем \"(.*)\" как максимальную цену")
    public void priceTo(String s)  {
        ozonGoodsPage.price.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),s + "\n");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Допустим("Выбираем марку из списка")
    public void chooseMark(List<String> set){
        ozonGoodsPage.chooseMark(set);
    }

  /*  @Допустим("Выбираем марку из списка {string} {string}")
    public void selectBrands(String s1, String s2) throws InterruptedException {
        ozonGoodsPage.click(ozonGoodsPage.posmVse);
        Thread.sleep(3000);
        ozonGoodsPage.click(ozonGoodsPage.strPoisk);
        ozonGoodsPage.writeValue(ozonGoodsPage.strPoisk, s1);
        Thread.sleep(3000);
        ozonGoodsPage.strPoisk.clear();
        Thread.sleep(3000);
        ozonGoodsPage.click(ozonGoodsPage.strPoisk);
        Thread.sleep(3000);
        ozonGoodsPage.writeValue(ozonGoodsPage.strPoisk, s2);
        Thread.sleep(3000);   //то работает то нет
    }*/

    @Тогда("Добавляем 8 {string} товаров")
    public void addingToBasket(String chet) {
        String s = "нечетных";
        int j,k;
        if (ozonGoodsPage.allGoodsOnPage.size() > 17){
            k = 16;
        }
        else {k = ozonGoodsPage.allGoodsOnPage.size();
        }if (chet.equals(s)) { j = 0;
        } else { j = 1;
        }
        for (int i = j; i < k; i = i + 2) {
            String xpathPriceProduct = String.format(
                    "//div[@data-index='%s']//span[@data-test-id='tile-price']", k);
            int prodPrice = Integer.parseInt(DriverManager.getDriver().findElement(By.
                    xpath(xpathPriceProduct)).getText().replaceAll("\\D", ""));
            String pathProd = String.format(
                    "//div[@data-index='%s']//a[@id='name']", k);
            String prodName = DriverManager.getDriver().findElement(By.xpath(pathProd)).getText();
            setMAP(prodName, prodPrice);
            String xpathInBasket = String.format(
                    "//div[@data-index='%s']//span[contains(text(), 'В корзину')]", k);
            WebElement add = DriverManager.getDriver().findElement(By.xpath(xpathInBasket));
            ozonGoodsPage.jsScroll(add);
        }
    }

    @Когда("Проверяем товары в корзине на предмет их добавления")
    public void prodInBask() {
        ozonGoodsPage.basketClickk();
        Set<String> set = new HashSet<>();
        for (WebElement element : ozonBasketPage.list) {
            set.add(element.getText());
        }
        for (Map.Entry map : BasePageClass.MAP.entrySet()) {
            if (!set.contains(map.getKey())) {Assert.fail("ОПЯТЬ НЕТ ТОВАРА !!1" + map.getKey());}
        }
    }

    @Пусть("Проверяем цены товаров в корзине")
    public void checkBaskPrice() {
         int sumAd = 0;
         int baskSum = ozonBasketPage.getItogSum();
            for (Map.Entry<String, Integer> pair : ozonGoodsPage.map.entrySet()) {
                sumAd = sumAd + pair.getValue();
            }       Assert.assertEquals("Цена не соответсвует(А именно какая то скидка, и цена меняется в итоге"
                , sumAd, baskSum + "\n");
    }

    @То("Удаляем все из корзины")
    public void cleaningBasket(){
        ozonBasketPage.removeItemsFromBasket();
    }

    @Тогда("Выйти из аккаунта")
    public void logOut(){
        ozonMainPage.logout();
    }

    @А("Корзина очищена от товаров")
    public void basketHasNoItemsCheck(){
        String s = ozonBasketPage.getBasketIsEmptyText().getText();
        Assert.assertEquals("Корзина пуста",s);
    }
}
