package stepdefinitions;

import Baseclass.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import wrapperMethods.Wrappermethods;

import static wrapperMethods.Wrappermethods.getScreenshot;

public class inputvalidation {
    public static WebDriver driver;
    public static ExtentTest link_test;
    public boolean  inputvalidation;
    BaseClass utilities = new BaseClass();

    public void setUp() {
        utilities.setup();
        driver = utilities.getDriver();
        link_test = utilities.getLink_test();
    }
    @Given("User launch the url for input")
    public void userLaunchTheUrlForInput()
    {
        setUp();
    }

    @When("User click on home page and give input")
    public void userClickOnHomePageAndGiveInput()
    {
        Wrappermethods home = new Wrappermethods(driver);
        try {
            inputvalidation = home.inputhvalidations();
            System.out.println(inputvalidation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the data should enter")
    public void theDataShouldEnter()
    {
        if (inputvalidation)
        {
            link_test.pass("input validated successfully");
        } else {
            try {
                link_test.fail("not able to enter input", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        driver.quit();
    }
}
