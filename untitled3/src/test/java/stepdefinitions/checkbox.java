package stepdefinitions;

import Baseclass.BaseClass;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import wrapperMethods.Wrappermethods;

import static wrapperMethods.Wrappermethods.getScreenshot;

public class checkbox extends BaseClass {
    BaseClass utilities = new BaseClass();
    boolean checkboxselected;

    public void setUp() {
        utilities.setup();
        driver = utilities.getDriver();
        link_test = utilities.getLink_test();
    }
    @Given("User launch the browser for checkbox")
    public void userLaunchTheBrowserForCheckbox()
    {
        setUp();
    }

    @When("User click on checkbox and select")
    public void userClickOnCheckboxAndSelect()
    {
        Wrappermethods home = new Wrappermethods(driver);
        try {
            checkboxselected = home.checkboxvalidation();
            System.out.println(checkboxselected);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("The checkbox value should be selected")
    public void theCheckboxValueShouldBeSelected()
    {
        if (checkboxselected)
        {
            link_test.pass("checkbox selected successfully");
        } else {
            try {
                link_test.fail("Failed to select checkbox", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        driver.quit();
    }
}
