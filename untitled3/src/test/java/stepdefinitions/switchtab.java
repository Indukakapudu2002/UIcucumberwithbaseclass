package stepdefinitions;
import Baseclass.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import wrapperMethods.Wrappermethods;

import java.util.Objects;

import static wrapperMethods.Wrappermethods.getScreenshot;

public class switchtab{
    public static WebDriver driver;
    public static ExtentTest link_test;
    public String tabtitle;
    BaseClass utilities = new BaseClass();
    public void setUp() {
        utilities.setup();
        driver = utilities.getDriver();
        link_test = utilities.getLink_test();
    }

    @Given("User launch the switch tab")
    public void userLaunchTheSwitchTab()
    {
        setUp();
    }

    @When("User click on switchtab")
    public void userClickOnSwitchtab()
    {
        Wrappermethods home = new Wrappermethods(driver);
        try {
            tabtitle = home.switchtab();
            System.out.println(tabtitle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("user should switch to new tab")
    public void userShouldSwitchToNewTab()
    {
        if (Objects.equals(tabtitle, "QAClick Academy - A Testing Academy to Learn, Earn and Shine")) {
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
