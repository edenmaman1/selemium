package lusha.ui.infra.pages;

import lusha.utils.UiActions;
import lusha.utils.entities.FormDetails;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static lusha.utils.CommonOps.*;

public class PlaceOrderFormPage {

    WebDriver driver;

    public PlaceOrderFormPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"name\"]")
    static WebElement nameTextElement;

    @FindBy(xpath = "//*[@id=\"country\"]")
    static WebElement countryTextElement;

    @FindBy(xpath = "//*[@id=\"city\"]")
    static WebElement cityTextElement;

    @FindBy(xpath = "//*[@id=\"card\"]")
    static WebElement creditCardNumberTextElement;

    @FindBy(xpath = "//*[@id=\"month\"]")
    static WebElement creditCardMonthTextElement;

    @FindBy(xpath = "//*[@id=\"year\"]")
    static WebElement creditCardYearTextElement;

    @FindBy(xpath = "//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")
    static WebElement purchaseButtonElement;

    @FindBy(xpath = "//*[@id=\"totalm\"]")
    static WebElement totalAmountToPayElement;


    public static void fillNewOrderFormDetails(){
        FormDetails orderFormDetails = new FormDetails();
        UiActions.insertText(nameTextElement,orderFormDetails.name);
        UiActions.insertText(countryTextElement,orderFormDetails.country);
        UiActions.insertText(cityTextElement,orderFormDetails.city);
        UiActions.insertText(creditCardNumberTextElement,orderFormDetails.creditCardNumber);
        UiActions.insertText(creditCardMonthTextElement,orderFormDetails.month);
        UiActions.insertText(creditCardYearTextElement,orderFormDetails.year);
        UiActions.click(purchaseButtonElement);
    }

    public static int getTotalAmountToPay() throws InterruptedException {
        waitForElementToBeVisible();
        return Integer.parseInt(UiActions.getElementText(totalAmountToPayElement).replace("Total: ",""));
    }
}
