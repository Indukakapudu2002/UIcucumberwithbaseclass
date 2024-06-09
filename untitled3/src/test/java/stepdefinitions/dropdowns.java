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
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import wrapperMethods.Wrappermethods;

import static wrapperMethods.Wrappermethods.getScreenshot;

public class dropdowns {

    public static WebDriver driver;
    public static ExtentTest link_test;
    public boolean dropdownselected;
    BaseClass utilities = new BaseClass();

    public void setUp() {
        utilities.setup();
        driver = utilities.getDriver();
        link_test = utilities.getLink_test();
    }

    @Given("User launch the browser for dropdown")
    public void userLaunchTheBrowserForDropdown()
    {
        setUp();
    }

    @When("User click on dropdown and select value")
    public void userClickOnDropdownAndSelectValue()
    {
        Wrappermethods home = new Wrappermethods(this.driver);
        try {
            dropdownselected = home.handledropdowns();
            System.out.println(dropdownselected);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Then("The value should be selected")
    public void theValueShouldBeSelected()
    {
        if (dropdownselected)
        {
            link_test.pass("Dropdown validated successfully");
        } else {
            try {
                link_test.fail("Failed to select dropdown", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        driver.quit();
    }
}
