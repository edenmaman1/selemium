package exercise02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.SQLOutput;
import java.util.List;


public class ex01 {
    SoftAssert soft = new SoftAssert();
    WebDriver driver;
    String url="https://demo.nopcommerce.com";
    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterClass
    public void stopSession() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
    @BeforeMethod
    public void beforeMethod(){
        driver.navigate().to(url);
    }


    @Test
    public void testA11() throws InterruptedException {
        System.out.println("find by id-negative");
        driver.findElement(By.xpath("(//a[@href='/electronics'])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[1]/div/div[1]/div/div/a")).click();
        Thread.sleep(1000);
        String text1 = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[1]/h1")).getText();
        soft.assertEquals(text1,"Camera & photo");
        List<WebElement> list = driver.findElements(By.xpath("//h2[@class='product-title']"));
        soft.assertTrue(list.size()>0);
        list.forEach((elem)->{
            System.out.println(elem.getText());
        });
        soft.assertAll();

    }

    @Test
    public void testA12() throws InterruptedException {
        System.out.println("Sorting be price");
        driver.findElement(By.xpath("(//a[@href='/electronics'])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[1]/div/div[1]/div/div/a")).click();
        Thread.sleep(2000);
        Select select = new Select(driver.findElement(By.id("products-orderby")));
        select.selectByVisibleText("Price: High to Low");
        Thread.sleep(2000);
    }


}