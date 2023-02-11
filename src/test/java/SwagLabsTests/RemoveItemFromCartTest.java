package SwagLabsTests;

import SwagLabsBase.SwagLabsBaseTest;
import SwagLabsPages.CartPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
public class RemoveItemFromCartTest extends SwagLabsBaseTest {

    @BeforeMethod
    public void paeSetUp() {
        driver.manage().window().maximize();
        driver.get(loginUrl);
    }

    public int populateCart() throws InterruptedException {
        int brojac = 0;
        for (int i = 0; i < productPage.InventoryList.size(); i++) {
            inventoryItemPage.clickOnAddToCartButton();
            Thread.sleep(3000);
            brojac = brojac + 1;
        }
        return brojac;
    }

    public int RemoveItemFromCart() throws InterruptedException {
        int remove_counter = 0;
        List<WebElement> novaLista = new ArrayList<>();
        for (int i = 0; i < cartPage.removeButton.size(); i++) {
            if (cartPage.removeButton.get(i).getText().equalsIgnoreCase("REMOVE")) {
                novaLista.add(cartPage.removeButton.get(i));
            }
        }

        for (int j = 0; j < novaLista.size(); j++) {
            novaLista.get(j).click();
            remove_counter = remove_counter + 1;
        }
        return remove_counter;
    }
    public void assertCartPageIsDisplayed(){
        String title = "YOUR CART";
        String URL = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(cartPage.title.getText(),title);
        Assert.assertEquals(driver.getCurrentUrl(), URL);
        Assert.assertTrue(isDisplayed(cartPage.checkoutButton));

    }


    @Test
    public void PopulateAndRemoveItemsFromCart() throws InterruptedException {
        for (int i = 1; i <= excelReader.getLastRow("log2"); i++) {
        String validUsername = excelReader.getStringData("log2", i, 0);
        String validPassword = excelReader.getStringData("log2", i, 1);

        loginPage.insertUsername(validUsername);
        waitForClickability(loginPage.Password);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertFalse(isDisplayed(productPage.numberOfItems), "Cart is not empty for new logged in user " + validUsername);
        int brojac = populateCart();
        Assert.assertEquals(brojac, Integer.parseInt(productPage.numberOfItems.getText()), "Cart doesn't contain all added item for user " + validUsername);
        Assert.assertTrue(isDisplayed(productPage.numberOfItems), "Cart is empty for user " + validUsername);

        productPage.clickOnCart();
        assertCartPageIsDisplayed();
        int remove_counter = RemoveItemFromCart();
        Assert.assertEquals(brojac, remove_counter, "Not all items are removed from cart");
        productPage.clickOnSideMenu();
        productPage.clickOnLogOutButton();

        }
}

}
