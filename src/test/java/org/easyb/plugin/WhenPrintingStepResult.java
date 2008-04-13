package org.easyb.plugin;

import static org.disco.easyb.util.BehaviorStepType.SPECIFICATION;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.containsString;

public class WhenPrintingStepResult {
    @Test
    public void shouldIncludeStepNameAndOutcomeInOutput() {
        StepResult result = new StepResult("foo", SPECIFICATION, RunResult.SUCCESS);
        assertThat(result.toString(), containsString("foo"));
        assertThat(result.toString(), containsString("SUCCESS"));
    }
}
