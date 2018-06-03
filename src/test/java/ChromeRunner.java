import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ChromeRunner extends JUnitStories {


    @Override
    public Configuration configuration(){
        return new MostUsefulConfiguration().useStoryReporterBuilder(getReporter()).
                useStoryLoader(new LoadFromURL());
    }


    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new ChromeSteps());
    }

    @Override
    protected List<String> storyPaths() {
        String path = "stories/**/*.story";
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromPath("").getFile(),
                Collections.singletonList(path),
                new ArrayList<>(), "file:");
    }

    private StoryReporterBuilder getReporter(){
        return new StoryReporterBuilder().withPathResolver(new FilePrintStreamFactory.ResolveToSimpleName()
        ).withDefaultFormats().withFormats(Format.CONSOLE, Format.HTML);
    }
}
