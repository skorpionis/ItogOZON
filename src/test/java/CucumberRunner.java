import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Ariec on 27.07.2019.
 */
    @RunWith(Cucumber.class)
    @CucumberOptions(features = "src/test/resources/features"
            ,glue = {"Steps"}
            ,plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"}
    )
    public class CucumberRunner {}


