package lusha.ui.infra.pages;

import io.qameta.allure.Step;
import lusha.utils.UiActions;
import lusha.utils.entities.FormDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static lusha.utils.CommonOps.*;
import static lusha.utils.UiActions.getElementText;


public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"totalp\"]")
    static WebElement totalAmountElement;

    @FindBy(xpath = "//*[@id=\"page-wrapper\"]/div/div[2]/button")
    static WebElement placeOrderButtonElement;

    @FindBy(xpath = "//*[@id=\"tbodyid\"]")
    static WebElement productsTableElement;


    @FindBy(xpath = "//*[@class=\"btn btn-primary\"]")
    static WebElement purchaseButtonElement;

    String productsTableContentElement = "//*[@id=\"tbodyid\"]/tr";

    @Step("Get total cart amount")
    public int getCartTotalAmount() throws InterruptedException {
      waitForElement(productsTableElement);
      waitForElementToBeVisible();
      return Integer.parseInt(getElementText(totalAmountElement));
    }

    @Step("Validate if item exist in cart")
    public boolean isItemExistInCart(String name){
        int numberOfProductsInCart = driver.findElements(By.xpath(productsTableContentElement)).size();
        for(int i = 1; i<= numberOfProductsInCart; i++){
            if((driver.findElements(By.xpath((String.format("//*[@id=\"tbodyid\"]/tr[%d]/td",i)))).get(1).getText()).equals(name)) return true;
        }
        return false;
    }

    @Step("Place new order")
    public void placeNewOrder(){
        UiActions.click(placeOrderButtonElement);
    }
}
