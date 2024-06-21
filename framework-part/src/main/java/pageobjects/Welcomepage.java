package pageobjects;

import Abstractpac.Abstractclass;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Welcomepage extends Abstractclass {

    WebDriver driver;
    public Welcomepage(WebDriver driver) {

        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userEmail")
    WebElement useremail;
    @FindBy(id = "userPassword")
    WebElement password;
    @FindBy(id="login")
    WebElement loginbutton;

    @FindBy(css = "[class*='flyInOut']")
    WebElement erromessage;
    //.ng-tns-c4-4.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
    public void actionLogin(String userEmail, String pass)
    {
        useremail.sendKeys(userEmail);
        password.sendKeys(pass);
        loginbutton.click();
    }
    public void actionUrl(String Url)
    {
        driver.get(Url);
    }

    public String getErrormsg()
    {
       visiblityOfElement(erromessage);
       return erromessage.getText();
    }

}
