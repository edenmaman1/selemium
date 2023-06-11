package exercises.ex01;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class ex02 {
    WebDriver driver;
    String url="http://www.saucedemo.com";
    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterClass
    public void stopSession() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
    @BeforeMethod
    public void beforeMethod(){
        driver.navigate().to(url);
    }

    @Test
    public void test01a(){
        String elem = driver.findElement(new By.ByXPath("//*[@id=\"login_credentials\"]")).getText();
        String elem1 = driver.findElement(new By.ByClassName("login_password")).getText();

        List<String> userNameList = List.of(elem.split("\n"));
        List<String> passwordList = List.of(elem1.split("\n"));

        driver.findElement(new By.ById("user-name")).sendKeys(userNameList.get(1));
        driver.findElement(new By.ById("password")).sendKeys(passwordList.get(1));
        driver.findElement(By.id("login-button")).click();

        String list = driver.findElement(By.className("inventory_list")).getText();
        List<String> productsList = List.of(list.split("\n"));
        for (int i =0 ; i< productsList.size(); i++){
            if(productsList.get(i).contains("Sauce")){
                System.out.print("Product name: " +productsList.get(i));
            }
            if(productsList.get(i).contains("$")) {
                System.out.println(" Product price :" +productsList.get(i));
            }
        }
    }
}
