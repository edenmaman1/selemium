package exercises.ex01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ex03 {
    WebDriver driver;
    String url="http://www.loudev.com";
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
    public void test1(){
        List<String> str = List.of((driver.findElement(By.className("ms-list")).getText()).split("\n"));
        AtomicInteger num = new AtomicInteger(3);
        SoftAssert softAssert = new SoftAssert();
        str.forEach((elem) ->{
            String str1 = elem.replace("elem ","");
            softAssert.assertEquals(str1,String.valueOf(num.get()));
            num.getAndIncrement();
        } );
       softAssert.assertAll();
    }
}
