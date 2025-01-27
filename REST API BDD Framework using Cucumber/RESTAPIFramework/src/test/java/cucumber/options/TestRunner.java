package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

//tags are used to run the specified tests mentioned with the tag name in the feature file.
//glue is used to specify the StepDefinition class file path.
//features are used to specify the path of the feature file which is being used.
//plugin is used to store the output as json and after colon where the json needs to be stored that path we need to give.
//@CucumberOptions(features="src/test/java/features", glue= {"StepDefinitions"},tags = "@DeletePlace")
@CucumberOptions(features="src/test/java/features", plugin="json:target/jsonReports/cucumber-report.json",glue= {"StepDefinitions"})
public class TestRunner {

}
