package lusha.utils;

import io.qameta.allure.Step;
import lusha.utils.entities.Product;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class UiActions {
    @Step("Click on element")
    public static void click(WebElement element){
        element.click();
    }

    @Step("Insert text")
    public static void insertText(WebElement element,String text){
        element.sendKeys(text);
    }

    @Step("Click on popup")
    public static void clickOnPopUpMessage(Alert popupElement){
        popupElement.accept();
    }

    @Step("Get elements list")
    public static String getElementText(WebElement element){
        return element.getText();
    }

    @Step("Calculate expected total amount")
    public static int calculateExpectedTotalAmount(List<Product> productList){
        AtomicInteger totalAmount = new AtomicInteger();
        productList.forEach((product)->{
            totalAmount.addAndGet(product.itemPrice);
        });
        return totalAmount.get();
    }

    @Step("Get product's table content")
    public static void getProductTableContent(WebElement element){

    }
}
