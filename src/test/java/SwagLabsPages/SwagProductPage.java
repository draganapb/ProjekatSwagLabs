package SwagLabsPages;

import SwagLabsBase.SwagLabsBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SwagProductPage extends SwagLabsBaseTest {
    public SwagProductPage() {
        PageFactory.initElements(driver, this);
    }

    public @FindBy(id = "shopping_cart_container")
    WebElement cart;

    public @FindBy(className = "shopping_cart_badge")
    WebElement numberOfItems;
    public @FindBy(className = "active_option")
    WebElement sortMenu;

    public @FindBy(className = "title")
    WebElement title;

    public @FindBy(id = "react-burger-menu-btn")
    WebElement sideMenu;

    public @FindBy(className = "inventory_item_name")
    List<WebElement> InventoryList;
    public @FindBy(id = "logout_sidebar_link")
    WebElement logOutButton;

    public void clickOnSortMenu() {
        sortMenu.click();
    }

    public void clickOnSideMenu() {
        sideMenu.click();
    }


    public void checkAddedItemsInCart() {
        cart.click();
    }

    public void clickOnLogOutButton() {
        logOutButton.click();
    }

    public void clickOnCart() {
        cart.click();
    }

    }
