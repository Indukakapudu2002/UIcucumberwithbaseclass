package stepdefinitions;

import Baseclass.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import wrapperMethods.Wrappermethods;

import java.util.Objects;

import static wrapperMethods.Wrappermethods.getScreenshot;

public class switchalert {
    public static WebDriver driver;
    public static ExtentTest link_test;
    public String alerttitle;
    BaseClass utilities = new BaseClass();

    public void setUp() {
        utilities.setup();
        driver = utilities.getDriver();
        link_test = utilities.getLink_test();
    }
    @Given("User launch the switch alert")
    public void userLaunchTheSwitchAlert()
    {
        setUp();
    }

    @When("User click on switchalert")
    public void userClickOnSwitchalert()
    {
        Wrappermethods home = new Wrappermethods(driver);
        try {
            alerttitle = home.switchalert();
            System.out.println(alerttitle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Then("user should switch to new alert")
    public void userShouldSwitchToNewAlert()
    {
        if (Objects.equals(alerttitle, "Hello , share this practice page and share your knowledge")) {
            link_test.pass("alert validated successfully");
        } else {
            try {
                link_test.fail("Alert failed", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        driver.quit();
    }
}
