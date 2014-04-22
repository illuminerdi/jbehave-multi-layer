package restBehaveTest;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import restBehaveTest.lib.TestNGStory;
import restBehaveTest.types.BookUnitSteps;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.text.MessageFormat.format;


public class ICanCreateABook extends TestNGStory {

    private static final Logger logger = Logger.getLogger(ICanCreateABook.class.getCanonicalName());

    private static final Map<String, Object> layerMap = new HashMap<String, Object>();
    private String testLayer;

    static {
        layerMap.put("unit", new BookUnitSteps());
        layerMap.put("functional", new BookFunctionalSteps());
    }

    @BeforeClass
    @Parameters({"test.layer"})
    public void beforeClass(@Optional("unit") String testLayer) {
        this.testLayer = testLayer;
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                // where to find the stories
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .usePendingStepStrategy(new FailingUponPendingStep())
                        // CONSOLE and TXT reporting
                .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT));
    }

    // Here we specify the steps classes
    @Override
    public InjectableStepsFactory stepsFactory() {
        // varargs, can have more that one steps classes
        Object steps = layerMap.get(testLayer);
        logger.info(format("Running with test layer [{0}] using steps [{1}]",
                testLayer, steps.getClass().getSimpleName()));
        return new InstanceStepsFactory(configuration(), layerMap.get(testLayer));
    }
}
