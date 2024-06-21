package testpac.Testcomponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;
import pageobjects.Welcomepage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Welcomepage page;

    public WebDriver initializeDriver() throws IOException {
        System.out.println("Initializing WebDriver...");
        Properties prop = new Properties();
        FileInputStream fileinput = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//programResources//GlobalData.properties");
        prop.load(fileinput);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
        //String browserName = prop.getProperty("browser");
        System.out.println("Browser specified in properties: " + browserName);
        if (browserName.equalsIgnoreCase("chrome")) {
            System.out.println("Initializing ChromeDriver...");
            driver = new ChromeDriver();
            System.out.println("ChromeDriver initialized successfully.");
        } else if (browserName.equalsIgnoreCase("edge")) {
            //edge browser initialization
            System.out.println("Initializing EdgeDriver...");
            driver = new EdgeDriver();
            System.out.println("EdgeDriver initialized successfully.");
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //firefox browser initialization
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        System.out.println("WebDriver initialized successfully.");
        return driver;
    }

    public void loggingIn(String email, String password) throws IOException {
        System.out.println("Logging in...");
        driver = initializeDriver();
        page = new Welcomepage(driver);
        page.actionUrl("https://rahulshettyacademy.com/client");
        page.actionLogin(email, password);
    }
    public String getScreenshort(String testcaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts =(TakesScreenshot)driver;
        File Source = ts.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "/reports/" + testcaseName + ".png";
        File destination = new File(filePath);
        FileUtils.copyFile(Source,destination);
        return System.getProperty("User.dir")+"//reports//"+testcaseName+".png";
    }
    public List<HashMap<String,String>> dataReader(String Filepath) throws IOException{

        //Read jason data to string
        String jsonContent= FileUtils.readFileToString(new File(Filepath), StandardCharsets.UTF_8);
        //string to list of hashmaps
        ObjectMapper mapper  = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
        return data;

    }


}


