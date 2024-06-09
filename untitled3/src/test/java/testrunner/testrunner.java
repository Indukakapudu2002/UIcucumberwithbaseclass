package testrunner;


import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@io.cucumber.junit.CucumberOptions(
        features = "src/test/resources/features/stepdefinitions.homepage.feature",
        glue ="homepage.java",
        monochrome = true,
        plugin = { "pretty","html:target/cucumber-reports/reports1.html"}
)


public class testrunner

{

}