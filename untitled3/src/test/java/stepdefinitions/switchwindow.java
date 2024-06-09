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

public class switchwindow {
    public static WebDriver driver;
    public static ExtentTest link_test;
    public String windowtitle;
    BaseClass utilities = new BaseClass();

    public void setUp() {
        utilities.setup();
        driver = utilities.getDriver();
        link_test = utilities.getLink_test();
    }
    @Given("User launch the switch window")
    public void userLaunchTheSwitchWindow()
    {
        setUp();
    }

    @When("User click on switchwindow")
    public void userClickOnSwitchwindow()
    {
        Wrappermethods home = new Wrappermethods(driver);
        try {
            windowtitle = home.switchwindow();
            System.out.println(windowtitle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("user should switch to new window")
    public void userShouldSwitchToNewWindow()
    {
        if (Objects.equals(windowtitle, "QAClick Academy - A Testing Academy to Learn, Earn and Shine")) {
            link_test.pass("search validated successfully");
        } else {
            try {
                link_test.fail("invalid search results", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        driver.quit();
    }
}
