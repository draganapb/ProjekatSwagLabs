package SwagLabsPages;

import SwagLabsBase.SwagLabsBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
public class CartPage extends SwagLabsBaseTest {
    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public @FindBy  (className = "title")
    WebElement title;

    public @FindBy (id = "continue-shopping")
    WebElement continueShoppingButton;

    public @FindBy (id = "checkout")
    WebElement checkoutButton;

    public @FindBy(tagName = "button")
    List<WebElement> removeButton;

    public void clickOnContinueShoppingButton(){
        continueShoppingButton.click();
    }

    public void goAndCheckout(){
        checkoutButton.click();
    }
}
