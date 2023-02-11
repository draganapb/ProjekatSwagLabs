package SwagLabsTests;

import SwagLabsBase.SwagLabsBaseTest;
import SwagLabsPages.InventoryItemPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddingToCartTest extends SwagLabsBaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get(loginUrl);
    }

    public void logIn(String Username, String Password) throws InterruptedException {
        loginPage.insertUsername(Username);
        Thread.sleep(3000);
        loginPage.insertPassword(Password);
        loginPage.clickOnLoginButton();
    }


    public int openItemAddToCartAndGoBack() throws InterruptedException {
        int brojac = 0;
        for (int i = 0; i < productPage.InventoryList.size(); i++) {
            String productName = productPage.InventoryList.get(i).getText();
            productPage.InventoryList.get(i).click();
            Thread.sleep(3000);
            Assert.assertEquals(productName, inventoryItemPage.productTitle.getText(), "Product name doesn't match between inventory list and product page");
            inventoryItemPage.clickOnAddToCartButton();
            brojac = brojac + 1;
            Thread.sleep(1000);
            inventoryItemPage.clickOnBackOfProductsButton();
            Thread.sleep(3000);
        }
        return brojac;
    }
    public int AddToCartDirect() throws InterruptedException {
        int brojac = 0;
        for (int i = 0; i < productPage.InventoryList.size(); i++) {
            inventoryItemPage.clickOnAddToCartButton();
                brojac = brojac + 1;
                Thread.sleep(1000);
        }
        return brojac;
    }

    @Test
    public void addItemToCartIndirectly() throws InterruptedException {
        for (int i = 1; i <= excelReader.getLastRow("log2"); i++) {
            String validUsername = excelReader.getStringData("log2", i, 0);
            String validPassword = excelReader.getStringData("log2", i, 1);

            logIn(validUsername, validPassword);
            Assert.assertFalse(isDisplayed(productPage.numberOfItems), "Cart is not empty for new logged in user " + validUsername);
            int brojac = openItemAddToCartAndGoBack();
            Assert.assertTrue(isDisplayed(productPage.numberOfItems), "Cart is empty for user " + validUsername);
            Assert.assertEquals(brojac, Integer.parseInt(productPage.numberOfItems.getText()), "Cart doesn't contain all added item for user " + validUsername);
            Thread.sleep(3000);
            productPage.clickOnSideMenu();
            productPage.clickOnLogOutButton();
        }

    }

    @Test
    public void addItemToCartDirectly() throws InterruptedException {
        for (int i = 1; i <= excelReader.getLastRow("log2"); i++) {
            String validUsername = excelReader.getStringData("log2", i, 0);
            String validPassword = excelReader.getStringData("log2", i, 1);
            logIn(validUsername, validPassword);

            Assert.assertFalse(isDisplayed(productPage.numberOfItems), "Cart is not empty for new logged in user " + validUsername);
            Thread.sleep(4000);
            int brojac = AddToCartDirect();
            Assert.assertTrue(isDisplayed(productPage.numberOfItems), "Cart is empty for new logged in use " + validUsername);
            Assert.assertEquals(brojac, Integer.parseInt(productPage.numberOfItems.getText()), "Cart doesn't contain all added item for user " + validUsername);
            productPage.clickOnSideMenu();
            productPage.clickOnLogOutButton();

        }

    }
    @AfterMethod

    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
