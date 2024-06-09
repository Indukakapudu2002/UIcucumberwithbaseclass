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

public class Radiobuttons {
    public static WebDriver driver;
    public static ExtentTest link_test;
    public boolean  radiobuttonvalidation;
    BaseClass utilities = new BaseClass();

    public void setUp() {
        utilities.setup();
        driver = utilities.getDriver();
        link_test = utilities.getLink_test();
    }

    @Given("User launch the url for radio button")
    public void userLaunchTheUrlForRadioButton() {
       setUp();

    }

    @When("User click on radio button")
    public void userClickOnRadioButton() {
        Wrappermethods home = new Wrappermethods(driver);
        try {
            radiobuttonvalidation = home.handleradiobutton();
            System.out.println(radiobuttonvalidation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the radio button should be selected")
    public void theRadioButtonShouldBeSelected()
    {
        System.out.println("then");
        if (radiobuttonvalidation)
        {
            link_test.pass("Radio buttons validated successfully");
        } else {
            try {
                link_test.fail("Failed to select radio buttons", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        driver.quit();
    }
}
