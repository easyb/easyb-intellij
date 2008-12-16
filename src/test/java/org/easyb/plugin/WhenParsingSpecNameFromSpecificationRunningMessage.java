package org.easyb.plugin;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertThat;
import org.easyb.plugin.ui.SpecificationRunningParser;
import static org.hamcrest.CoreMatchers.is;

public class WhenParsingSpecNameFromSpecificationRunningMessage {
    private SpecificationRunningParser parser;

    @Before
    public void inContext() {
        parser = new SpecificationRunningParser();
    }

    @Test
    public void shouldHandleSpaces() {
        assertThat(parser.parseSpecNameFrom("Running my spec story (MySpec.story)"), is("my spec"));
    }

    @Test
    public void shouldHandleWordStoryInStoryName() {
        assertThat(parser.parseSpecNameFrom("Running my story story (MyStory.story)"), is("my story"));
    }

    @Test
    public void shouldIdentifySpecificationRunningMessages() {
        assertThat(parser.isSpecificationRunningMessage("Running my spec story (MySpec.story)"), is(true));
    }
}
