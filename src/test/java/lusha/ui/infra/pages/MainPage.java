package lusha.ui.infra.pages;

import io.qameta.allure.Step;
import lusha.utils.UiActions;
import lusha.utils.entities.Product;
import lusha.utils.entities.ItemCategory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import static lusha.utils.CommonOps.*;
import static lusha.utils.UiActions.getElementText;

import java.time.Duration;
import java.util.List;



public class MainPage {

    WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"signin2\"]")
    static WebElement signUpButtonElement;

    @FindBy(xpath = "//*[@id=\"login2\"]")
    static WebElement loginButtonElement;

    @FindBy(xpath = "//*[@id=\"nameofuser\"]")
    static WebElement loggedInUser;

    @FindBy(xpath = "//*[@id=\"cartur\"]")
    static WebElement cartButtonElement;


    @FindBy(xpath = "//*[@id=\"cat\"]")
    static WebElement categoryTableElement;

    @FindBy(xpath = "//*[@id=\"navbarExample\"]/ul/li[1]/a")
    static WebElement homeButtonElement;

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div[2]/div/a")
    static WebElement addToCartButtonElement;

    @FindBy(xpath = "//*[text()='Phones']")
    static WebElement phonesCategoryElement;

    @FindBy(xpath = "//*[text()='Laptops']")
    static WebElement laptopsCategoryElement;

    @FindBy(xpath = "//*[text()='Monitors']")
    static WebElement monitorsCategoryElement;

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div[3]/div/div/h4/a")
    static WebElement nexus6Element;

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div[6]/div/div/h4/a")
    static WebElement macBookProElement;

    @Step("Get logged in username")
    public String getLoggedInUserDetails(){
         return getElementText(loggedInUser);
    }

    @Step("Wait for logged in username details")
    public void waitForUserDetails(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(loggedInUser));
    }

    @Step("Get products list")
    public List getProductsList(){
        return driver.findElements(By.xpath("//*[@id=\"itemc\"]"));
    }

    @Step("Get items by category name")
    public void filterItemByCategory(ItemCategory itemCategory) throws InterruptedException {
        List list = getProductsList();
        list.forEach((elem)->{
            switch (itemCategory){
                case Phone:
                    UiActions.click(phonesCategoryElement);
                    break;
                case Laptop:
                    UiActions.click(laptopsCategoryElement);
                    break;
                case Monitor:
                    UiActions.click(monitorsCategoryElement);
                    break;
                default:
                    System.out.println("Item not found");
                    break;
            }
        });
        waitForElementToBeVisible();
    }

    @Step("Add item to cart by name")
    public String addItemToCartByName(String itemName) throws InterruptedException {
        switch (itemName){
            case "Nexus 6":
                filterItemByCategory(ItemCategory.Phone);
                UiActions.click(nexus6Element);
                break;
            case "MacBook Pro":
                filterItemByCategory(ItemCategory.Laptop);
                UiActions.click(macBookProElement);
                break;
            default:
                System.out.println("Item not found");
                break;
        }
        waitForElement(addToCartButtonElement);
        UiActions.click(addToCartButtonElement);
        String popupMessage = popUpApprove();
        UiActions.click(homeButtonElement);
        return popupMessage;
    }

    @Step("Get items in cart")
    public void openCartPage(){
        UiActions.click(cartButtonElement);
    }

    @Step("Open main page")
    public void openMainPage(){
        UiActions.click(homeButtonElement);
        waitForElement(categoryTableElement);
    }

    @Step("Add new item to cart")
    public String addItemToCart(Product product) throws InterruptedException {
        openMainPage();
        String popupMessage = addItemToCartByName(product.itemName);
        openCartPage();
        return popupMessage;
    }
}
