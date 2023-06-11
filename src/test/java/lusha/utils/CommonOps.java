package lusha.utils;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import lusha.utils.entities.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class CommonOps extends Base{

    @BeforeSuite
    public void startSession() {
        initBrowser(getDataFromDataFile("BROWSER_TYPE"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @AfterSuite
    public void closeSession() {
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        driver.quit();
    }

    public static String getDataFromDataFile(String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("/Users/edenm/IdeaProjects/selemium/src/test/java/lusha/ui/infra/data/data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            System.out.println("Exception in reading XML file: " + e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    public void initBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            }
            case "edge": {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            default:
                throw new RuntimeException("Unsupported browser was provided!...");
        }

    }

    public User generateRandomUserDetails(){

        User user = new User();
        user.userName = Types.USER_NAME + getCurrentTimeMillis();
        user.password = Types.PASSWORD + getCurrentTimeMillis();
        return user;
    }

    public static String popUpApprove(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String popupMessage = alert.getText();
        UiActions.clickOnPopUpMessage(alert);
        return popupMessage;
    }

    public static void waitForElementToBeVisible() throws InterruptedException {
        //TODO change to wait until element visible
        Thread.sleep(3000);
    }

    public static void waitForElement(WebElement element)  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static String generateRandomString(int targetStringLength){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

}
