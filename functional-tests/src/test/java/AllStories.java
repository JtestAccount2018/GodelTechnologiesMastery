import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.godel.mastery.functionaltest.EmployeeServiceKeys;
import com.godel.mastery.functionaltest.storysteps.EmployeeStorySteps;
import com.godel.mastery.functionaltest.support.TestContext;

import net.serenitybdd.jbehave.SerenityStories;

public class AllStories extends SerenityStories {
    public AllStories() {
        findStoriesCalled("**/stories/*.story");
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        final TestContext<EmployeeServiceKeys> context = new TestContext<>();
        return new InstanceStepsFactory(
        // @formatter:off
            new MostUsefulConfiguration(), 
            new EmployeeStorySteps(context));
            // @formatter:on
    }
}
