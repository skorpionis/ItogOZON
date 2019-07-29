package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Ariec on 27.07.2019.
 */
public class OzonMainPage extends BasePageClass {


    @FindBy(xpath = "//span[contains(text(),'Войти')]")
    public List<WebElement> voiti;
    @FindBy(xpath = "//span[@data-test-id='header-my-ozon-title']")
    public List<WebElement> block;
    @FindBy(xpath = "//a[@data-test-id='goToEmailLink']")
    public WebElement emailAuthorization;
    @FindBy(xpath = "//input[@data-test-id='emailField']")
    public WebElement email;
    @FindBy(xpath = "//input[@data-test-id='passwordField']")
    public WebElement password;
    @FindBy(xpath = "//button[contains(text(), 'Войти')]")
    public WebElement submitBtn;
    @FindBy(xpath = "//span[@data-test-id='header-my-ozon-title']")
    public WebElement menushka;
    @FindBy(xpath = "//INPUT[@type='text']")
    public WebElement searchingField;
    @FindBy(xpath = "//button[@type='submit'][@class='m-search button default flat-button']")
    public WebElement searchBtn;
    @FindBy(xpath = "//button[@class='eda59']")
    public WebElement logOutEl;

    public void enter(){
        click(menushka);
    }
    public void withEmail(){
        click(emailAuthorization);
    }
    public void email(String log){
        fillTextField(email,log);
    }
    public void password(String log){
        fillTextField(password,log);
    }
    public void submit(){
        click(submitBtn);
    }
    public List<WebElement> getBlock() {
        return block;
    }
    public WebElement getMenushka() {
        return menushka;
    }

    public WebElement getLogOutEl() {
        return logOutEl;
    }
    public void logout(){
        WebElement element = findElement(block,"Мой кабинет");click(element);
        click(element.findElement((By) logOutEl));
    }

    public List<WebElement> getVoiti() {
        return voiti;
    }

    public WebElement getEmailAuthorization() {
        return emailAuthorization;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getSubmitBtn() {
        return submitBtn;
    }

    public WebElement getSearchingField() {
        return searchingField;
    }

    public WebElement getSearchBtn() {
        return searchBtn;
    }
}
