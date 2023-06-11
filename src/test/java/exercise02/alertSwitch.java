package exercise02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class alertSwitch {
WebDriver driver;
Alert alert;
SoftAssert softAssert;
String url = "";
@BeforeClass
public void beforeClass(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    softAssert = new SoftAssert();
}
    @BeforeMethod
    public void beforeMethod(){
        driver.navigate().to(url);
    }

    @Test
    public void test01(){
        System.out.println("Enter to iFrame");
        WebElement iframe = driver.findElement(By.id("iframe_name"));
        driver.switchTo().frame(iframe);
        System.out.println("Exit from iFrame");
        driver.switchTo().defaultContent();//enter to main
        driver.switchTo().parentFrame();//parent frame


    }
}
