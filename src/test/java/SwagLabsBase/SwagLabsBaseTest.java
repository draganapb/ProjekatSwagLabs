package SwagLabsBase;

import SwagLabsPages.CartPage;
import SwagLabsPages.InventoryItemPage;
import SwagLabsPages.LoginPage;
import SwagLabsPages.SwagProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class SwagLabsBaseTest {
    public static WebDriver driver;
    public WebDriverWait wdwait;
    public ExcelReader excelReader;
    public String loginUrl;
    public LoginPage loginPage;
    public SwagProductPage productPage;
    public CartPage cartPage;
    public InventoryItemPage inventoryItemPage;



    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        excelReader = new ExcelReader("src/test/java/TestData (7).xlsx");
        loginUrl = excelReader.getStringData("URL", 1,0);
        loginPage = new LoginPage();
        productPage = new SwagProductPage();
        cartPage = new CartPage();
        inventoryItemPage = new InventoryItemPage();
    }
    public boolean isDisplayed(WebElement element) {
        boolean webelement = false;
        try {
            webelement = element.isDisplayed();
        } catch (Exception e) {

        }
        return webelement;
    }
    public void waitForVisibility(WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickability(WebElement element) {
        wdwait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
