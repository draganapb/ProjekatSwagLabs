package SwagLabsTests;

import SwagLabsBase.ExcelReader;
import SwagLabsBase.SwagLabsBaseTest;
import SwagLabsPages.SwagProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class SwagLoginTest extends SwagLabsBaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException, InterruptedException {
        driver.manage().window().maximize();
        driver.get(loginUrl);
    }
    public void logIn(String Username, String Password) throws InterruptedException {
        loginPage.insertUsername(Username);
        Thread.sleep(3000);
        loginPage.insertPassword(Password);
        loginPage.clickOnLoginButton();
    }
    public void assertSuccessfulLogin(String username){
        String ProductPageURL = excelReader.getStringData("URL", 1, 1);
        Assert.assertFalse(isDisplayed(loginPage.LoginButton), "Not successfull login for " + username + "user");
        Assert.assertEquals(driver.getCurrentUrl(), ProductPageURL, "Not successfull login for " + username + "user");
        Assert.assertTrue(isDisplayed(productPage.title), "Not susccessfull login for " + username + " user");    }
    public void assertUnsuccessfulLogin(){
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl);
        Assert.assertTrue(isDisplayed(loginPage.LoginButton));
        Assert.assertTrue(isDisplayed(loginPage.ErrorNotification));
    }
    public void assertLogOut(String username) throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertTrue(isDisplayed(loginPage.LoginButton), "Logout failed for username" + username + " user");
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl, "Logout failed for username" + username + " user");
    }


    @Test (priority = 10)
    public void userCanLogIn() throws InterruptedException {
        for (int i = 1; i <= excelReader.getLastRow("log"); i++) {
            String validUsername = excelReader.getStringData("log", i, 0);
            String validPassword = excelReader.getStringData("log", i, 1);
            logIn(validUsername, validPassword);
            assertSuccessfulLogin(validUsername);
            Thread.sleep(3000);
            productPage.clickOnSideMenu();
            productPage.clickOnLogOutButton();

        }
    }

    @Test (priority = 15)
    public void userCanLogOut() throws InterruptedException {
        for (int i = 1; i <= excelReader.getLastRow("log2"); i++) {
            String validUsername = excelReader.getStringData("log", i, 0);
            String validPassword = excelReader.getStringData("log", i, 1);
            logIn(validUsername, validPassword);
            Thread.sleep(3000);
            productPage.clickOnSideMenu();
            productPage.clickOnLogOutButton();
            assertLogOut(validUsername);
        }
    }

        @Test (priority = 20)
    public void userCannotLoginWithInvalidUsername() throws InterruptedException {
            for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
                String invalidUsername = excelReader.getStringData("Login", i, 2);
                String validPassword = excelReader.getStringData("Login", i, 1);
                loginPage.insertUsername(invalidUsername);
                Thread.sleep(3000);
                loginPage.insertPassword(validPassword);
                loginPage.clickOnLoginButton();
                assertUnsuccessfulLogin();
                loginPage.clickOnErrorButton();
            }
        }
    @Test (priority = 30)
    public void userCannotLoginWithInvalidPassword() throws InterruptedException {
        for(int i = 1; i<= excelReader.getLastRow("Login"); i++){
            String validUsername = excelReader.getStringData("Login", 1,0);
            String invalidPassword = excelReader.getStringData("Login", 1, 3);
            loginPage.insertUsername(validUsername);
            Thread.sleep(3000);
            loginPage.insertPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            assertUnsuccessfulLogin();
            loginPage.clickOnErrorButton();
        }
    }
    @Test (priority = 40)
    public void userCannotLoginWithoutPassword() throws InterruptedException {
        for(int i = 1; i<= excelReader.getLastRow("log"); i++) {
            String validUsername = excelReader.getStringData("log", i, 0);
            System.out.println(validUsername);
            loginPage.insertUsername(validUsername);
            Thread.sleep(3000);
            loginPage.emptyPasswordField();
            loginPage.clickOnLoginButton();
            assertUnsuccessfulLogin();
            loginPage.clickOnErrorButton();
        }
    }

    @Test (priority = 50)
    public void userCannotLoginWithoutUsername() throws InterruptedException {
        for (int i = 1; i <= excelReader.getLastRow("log"); i++) {

            String validPassword = excelReader.getStringData("log", i, 1);
            Thread.sleep(4000);
            loginPage.emptyUsernameField();
            Thread.sleep(3000);
            loginPage.insertPassword(validPassword);
            loginPage.clickOnLoginButton();
            assertUnsuccessfulLogin();
            loginPage.clickOnErrorButton();
        }
    }
    @Test (priority = 60)
            public void userCannotLoginWithNoDataInput() throws InterruptedException {
            loginPage.emptyUsernameField();
            Thread.sleep(3000);
            loginPage.emptyPasswordField();
            loginPage.clickOnLoginButton();
            assertUnsuccessfulLogin();
            loginPage.clickOnErrorButton();
    }
@AfterMethod

    public void tearDown() throws InterruptedException {
    Thread.sleep(3000);
    driver.manage().deleteAllCookies();
    driver.quit();
    }
}






