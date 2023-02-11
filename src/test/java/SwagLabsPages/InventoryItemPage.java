package SwagLabsPages;

import SwagLabsBase.SwagLabsBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryItemPage extends SwagLabsBaseTest {
    public InventoryItemPage() {
        PageFactory.initElements(driver, this);
    }
    public @FindBy (tagName = "button")
    List<WebElement> addToCartButton;
//
    //(id = "add-to-cart-sauce-labs-backpack")
    public @FindBy (id = "back-to-products")
    WebElement backToProductsButton;

    public @FindBy (xpath = "/html/body/div/div/div/div[2]/div/div/div[2]/div[1]")
    WebElement productTitle;

    public void clickOnAddToCartButton(){
        for(int i=0; i<addToCartButton.size(); i++ ){
            if(addToCartButton.get(i).getText().equalsIgnoreCase("ADD TO CART")){
                addToCartButton.get(i).click();
            }
        }
    }

    public void clickOnBackOfProductsButton(){
        backToProductsButton.click();
    }


}
