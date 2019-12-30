package ch.heigvd.amt.project2.api.spec;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Based on the SpecificationTest class of Fruits written by Olivier Liechti
 * @author Nathanaël Mizuani
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/scenarios/", plugin = {"pretty", "html:target/cucumber"})
public class SpecificationTest {
 // No particular specifications
}
