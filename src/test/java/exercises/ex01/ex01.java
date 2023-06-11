package exercises.ex01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ex01 {
    WebDriver driver;
    String url="https://selenium.dev";
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
    public void testA01(){
        System.out.println("find by id");
        int size = driver.findElements(By.id("Layer_1")).size();
        System.out.println(size);
    }
    @Test
    public void testA11(){
        System.out.println("find by id-negative");
        int size = driver.findElements(By.id("Layer_2")).size();
        System.out.println(size);
    }
    @Test
    public void testA02() {
        System.out.println("find by class");
        int size = driver.findElements(By.className("navbar-logo")).size();
        System.out.println(size);
    }
    @Test
    public void testA12() {
        System.out.println("find by class-negative");
        int size = driver.findElements(By.className("navbar-logo1")).size();
        Assert.assertEquals(size,0);
    }
    @Test
    public void testA03() {
        System.out.println("find by xpath");
        int size = driver.findElements(new By.ByXPath("/html/body/header/nav/a")).size();
        Assert.assertEquals(size,1);
    }
    @Test
    public void testA13() {
        System.out.println("find by xpath-negative");
        int size = driver.findElements(new By.ByXPath("/html/body/header/nav/a1")).size();
        Assert.assertEquals(size,0);
    }

    @Test
    public void testB01() {
        System.out.println("find selenium ex");
        List<WebElement> links = driver.findElements(By.partialLinkText("Selenium"));

    }
    @Test
    public void testB02() {
        System.out.println("find number of total links");
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of total links: " + links.size());
    }

}