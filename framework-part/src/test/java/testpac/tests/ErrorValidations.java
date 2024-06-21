package testpac.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.ProductCheckPage;
import pageobjects.Productcatalog;
import testpac.Testcomponents.BaseTest;
import testpac.Testcomponents.Retry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test(dataProvider = "getData", retryAnalyzer = Retry.class)
    public void runerror(String email, String password) throws IOException {
        loggingIn(email, password);
        Assert.assertEquals("Login Successfully", page.getErrormsg());
        System.out.println("1st pass");
    }

    @Test(dependsOnMethods = "runerror")
    public void item() throws InterruptedException, IOException {
        Productcatalog productRef = new Productcatalog(driver);
        List<WebElement> product = productRef.getListOfProduct();
        List<WebElement> filterdItems =productRef.filterTheItems(product);
        productRef.click(filterdItems);
        productRef.clickCart();
    }

    @Test(dependsOnMethods = {"runerror","item"})
    public void itemcheck()
    {
        ProductCheckPage ProductcheckRef = new ProductCheckPage(driver);
        List<WebElement> insidecart = ProductcheckRef.itemListInsideCart();
        boolean contains = ProductcheckRef.toCheckItemPresent(insidecart);
        Assert.assertTrue(contains);

    }

    @DataProvider
    public Object[][] getData()
    {
        return new Object[][] {{"bheeshmacharya@gmail.com","Bheeshma@123"}};

    }
}
