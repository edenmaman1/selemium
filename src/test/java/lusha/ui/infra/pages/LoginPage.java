package lusha.ui.infra.pages;

import io.qameta.allure.Step;
import lusha.utils.UiActions;
import lusha.utils.entities.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id=\"loginusername\"]")
    private WebElement loginUserNameField;

    @FindBy(xpath = "//*[@id=\"loginpassword\"]")
    private WebElement loginPasswordElement;

    @FindBy(xpath = "//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")
    private WebElement loginButtonElement;

    @Step("Create new user")
    public void loginUserToSystem(User user){
        UiActions.click(MainPage.loginButtonElement);
        UiActions.insertText(loginUserNameField,user.userName);
        UiActions.insertText(loginPasswordElement,user.password);
        UiActions.click(loginButtonElement);
    }
}
