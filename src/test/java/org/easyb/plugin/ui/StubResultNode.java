package org.easyb.plugin.ui;

import org.disco.easyb.util.BehaviorStepType;
import org.easyb.plugin.Outcome;
import org.easyb.plugin.StepResult;

public class StubResultNode implements ResultNode {
    private StepResult result;

    public StubResultNode(StepResult result) {
        this.result = result;
    }

    public StepResult getResult() {
        return result;
    }

    public void add(Object child) {
    }

    @SuppressWarnings({"RedundantIfStatement"})
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StubResultNode that = (StubResultNode) o;

        if (result != null ? !result.equals(that.result) : that.result != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        return (result != null ? result.hashCode() : 0);
    }

    public static StubResultNode nodeFor(BehaviorStepType type, String phrase, Outcome outcome) {
        return new StubResultNode(new StepResult(phrase, type, outcome));
    }
}
