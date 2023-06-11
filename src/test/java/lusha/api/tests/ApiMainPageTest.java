package lusha.api.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import lusha.api.infra.ApiCallBuilder;
import lusha.ui.infra.pages.*;
import lusha.utils.CommonOps;
import lusha.utils.entities.Product;
import lusha.utils.entities.User;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.http.HttpRequest;
import java.util.HashMap;

import static lusha.api.infra.CommonOps.generateGetViewCartBodyRequest;
import static lusha.api.infra.CommonOps.getItemDetailsBodyRequest;
import static lusha.api.infra.Operations.executeApiCall;

public class ApiMainPageTest extends CommonOps {

    SoftAssert softAssert;

    @BeforeClass
    public void initPagesObjects() {
        driver.get(getDataFromDataFile("BASE_URL"));
        mainPage = new MainPage(driver);
        signUpPage = new SignUpPage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        placeOrderFormPage = new PlaceOrderFormPage(driver);
        softAssert = new SoftAssert();
    }


    @Test
    public void placeNewOrder() throws JsonProcessingException, InterruptedException {
        User userDetails = generateRandomUserDetails();
        Product product = new Product("Nexus 6", 650);
        signUpPage.createNewUser(userDetails);
        popUpApprove();
        loginPage.loginUserToSystem(userDetails);
        mainPage.waitForUserDetails();
        Cookie cookie = driver.manage().getCookieNamed("tokenp_");
        mainPage.addItemToCartByName(product.itemName);
        HttpRequest getViewCartRequest = ApiCallBuilder.buildViewCartRequestCall(generateGetViewCartBodyRequest(cookie.getValue()));
        Object getViewCartResponse = executeApiCall(getViewCartRequest);
        softAssert.assertEquals(((HashMap) getViewCartResponse).size(),1);
        System.out.println(getViewCartResponse);
        //todo parse response
        HttpRequest getItemDetailsRequest = ApiCallBuilder.buildViewRequestCall(getItemDetailsBodyRequest("3"));
        Object getItemDetailsResponse = executeApiCall(getItemDetailsRequest);
        softAssert.assertEquals((((HashMap) getItemDetailsResponse).get("price")),650.0);
        softAssert.assertEquals((((HashMap) getItemDetailsResponse).get("title")),product.itemName);
        softAssert.assertEquals(String.valueOf(((HashMap) getItemDetailsResponse).get("id")),"3");
        softAssert.assertAll();
    }

}
