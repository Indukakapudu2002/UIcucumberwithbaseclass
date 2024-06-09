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

public class homepage {
    public static WebDriver driver;
    public static ExtentTest link_test;
    BaseClass utilities = new BaseClass();
    public boolean  pagevalidation;

    public void setUp() {
        utilities.setup();
        driver = utilities.getDriver();
        link_test = utilities.getLink_test();
    }
    @Given("User launch the chromebrowser")
    public void user_launch_the_chromebrowser() {
        setUp();

    }
    @When("User click on home page")
    public void user_click_on_home_page() {
        Wrappermethods home = new Wrappermethods(driver);
        try {
            pagevalidation = home.homePageValidation();
            System.out.println(pagevalidation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Then("Home page title should be correct")
    public void home_page_title_should_be_correct() {
        System.out.println("then");
        if (!pagevalidation)
        {
            link_test.pass("Home page validated successfully");
        } else {
            try {
                link_test.fail("invalid home page", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        driver.quit();
    }

}
