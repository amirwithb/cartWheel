package amazon.stepDefinition;

import amazon.utilities.ConfigurationReader;
import amazon.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.logging.Logger;

public class Hooks {

    private final static Logger logger = Logger.getLogger("Hooks.class");

    @Before
    public void setup() {
        logger.info("::: Starting automation :::");
        logger.info("Browser type: "+ ConfigurationReader.getProperty("browser"));
        logger.info("URL: "+ConfigurationReader.getProperty("url"));
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            logger.info("Taking a screenshot");
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
        logger.info("::: Ending automation :::");
    }
}
