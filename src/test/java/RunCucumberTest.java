import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/CucumberTests.json",
                "junit:target/cucumber-reports/CucumberTests.xml",
                "html:target/cucumber-reports/index.html"},
        glue = {"src/test/java/steps"},
        features = "src/test/resources/features",
        monochrome = true)
public class RunCucumberTest {

}
