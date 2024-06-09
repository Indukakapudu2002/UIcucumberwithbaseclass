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

public class searchcourse {
    public static WebDriver driver;
    public static ExtentTest link_test;
    public boolean searchvalidation;
    BaseClass utilities = new BaseClass();

    public void setUp() {
        utilities.setup();
        driver = utilities.getDriver();
        link_test = utilities.getLink_test();
    }
    @Given("User launch the url")
    public void userLaunchTheUrl()
    {
        setUp();
    }

    @When("User click on home page and search course")
    public void userClickOnHomePageAndSearchCourse()
    {
        Wrappermethods home = new Wrappermethods(driver);
        try {
            home.homePageValidation();
            searchvalidation = home.searchcourses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("courses list should be correct")
    public void coursesListShouldBeCorrect()
    {
       if (searchvalidation) {
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
