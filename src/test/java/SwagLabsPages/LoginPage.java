package SwagLabsPages;

import SwagLabsBase.SwagLabsBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static SwagLabsBase.SwagLabsBaseTest.driver;

public class LoginPage extends SwagLabsBaseTest {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    public @FindBy(id = "user-name")
    WebElement Username;

    public @FindBy(id = "password")
    WebElement Password;

    public @FindBy(id = "login-button")
    WebElement LoginButton;

   public @FindBy(className = "error-button")
   WebElement ErrorNotification;

    public @FindBy (className = "error-button")
    WebElement ErrorButton;

    public void insertUsername(String username){
        Username.clear();
        Username.sendKeys(username);
    }
    public void emptyUsernameField(){
        Username.clear();
    }
    public void emptyPasswordField(){
        Password.clear();
    }
    public void insertPassword(String password){
        Password.clear();
        Password.sendKeys(password);
    }
    public void clickOnLoginButton(){
        LoginButton.click();
    }
    public void clickOnErrorButton(){
        ErrorButton.click();
    }
}
