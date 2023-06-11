package lusha.ui.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import lusha.ui.infra.pages.*;
import lusha.utils.CommonOps;
import lusha.utils.entities.Product;
import lusha.utils.entities.User;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static lusha.utils.UiActions.calculateExpectedTotalAmount;

public class TestMainPage extends CommonOps {

    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void initPagesObjects() {
        driver.get(getDataFromDataFile("BASE_URL"));
        mainPage = new MainPage(driver);
        signUpPage = new SignUpPage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        placeOrderFormPage = new PlaceOrderFormPage(driver);
    }
    @Test
    public void testCreateNewUser(){
        signUpPage.createNewUser(generateRandomUserDetails());
        String retVal = popUpApprove();
        Assert.assertEquals(retVal,"Sign up successful.");
    }

    @Test
    public void testLoginToSystem(){
        User userDetails = generateRandomUserDetails();
        signUpPage.createNewUser(userDetails);
        popUpApprove();
        loginPage.loginUserToSystem(userDetails);
        mainPage.waitForUserDetails();
        String loggedInUserDetails = mainPage.getLoggedInUserDetails();
        Assert.assertEquals(loggedInUserDetails,"Welcome "+userDetails.userName);
    }

    @Test
    public void testItemToChartAndValidation() throws InterruptedException {
        List<Product> productList = new ArrayList<>();
        Product product = new Product("Nexus 6",650);
        productList.add(product);
        int expectedTotalAmount = calculateExpectedTotalAmount(productList);
        String popupMessage = mainPage.addItemToCartByName(product.itemName);
        softAssert.assertEquals(popupMessage, "Product added");
        mainPage.openCartPage();
        int cartTotalAmount = cartPage.getCartTotalAmount();
        softAssert.assertTrue(cartPage.isItemExistInCart(product.itemName));
        softAssert.assertEquals(cartTotalAmount,expectedTotalAmount);
        softAssert.assertAll();
    }
    @Test
    public void testItemsToChart() throws InterruptedException {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("MacBook Pro",1100));
        productList.add(new Product("Nexus 6",650));
        int expectedTotalAmount = calculateExpectedTotalAmount(productList);
        productList.forEach((product)->{

            //TODO handle without thread sleep
            try {
            mainPage.openMainPage();
            mainPage.addItemToCartByName(product.itemName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        mainPage.openCartPage();
        int cartTotalAmount = cartPage.getCartTotalAmount();
        productList.forEach((product)->{
            softAssert.assertTrue(cartPage.isItemExistInCart(product.itemName));
        });
        softAssert.assertEquals(cartTotalAmount,expectedTotalAmount);
        softAssert.assertAll();
    }

    @Test
    public void placeNewOrder() throws InterruptedException {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("MacBook Pro",1100));
        productList.add(new Product("Nexus 6",650));
        int expectedTotalAmount = calculateExpectedTotalAmount(productList);
        productList.forEach((product)->{
            //TODO handle without thread sleep
            try {
                mainPage.openMainPage();
                mainPage.addItemToCartByName(product.itemName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        mainPage.openCartPage();
        cartPage.placeNewOrder();
        int payAmountFromPlaceOrderPage = placeOrderFormPage.getTotalAmountToPay();
        softAssert.assertEquals(payAmountFromPlaceOrderPage,expectedTotalAmount);
        placeOrderFormPage.fillNewOrderFormDetails();
        softAssert.assertAll();
    }
}
