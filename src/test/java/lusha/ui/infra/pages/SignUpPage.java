package lusha.ui.infra.pages;

import io.qameta.allure.Step;
import lusha.utils.UiActions;
import lusha.utils.entities.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {

    WebDriver driver;

    public SignUpPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[@id=\"sign-username\"]")
    private WebElement usernameTextField;

    @FindBy(xpath = "//*[@id=\"sign-password\"]")
    private WebElement passwordTextField;

    @FindBy(xpath = "//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")
    private WebElement submitButtonElement;


    @Step("Create new user")
    public void createNewUser(User user){
        UiActions.click(MainPage.signUpButtonElement);
        UiActions.insertText(usernameTextField,user.userName);
        UiActions.insertText(passwordTextField,user.password);
        UiActions.click(submitButtonElement);
    }
}
