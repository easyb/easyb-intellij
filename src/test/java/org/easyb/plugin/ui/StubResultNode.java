package org.easyb.plugin.ui;

import org.easyb.util.BehaviorStepType;
import org.easyb.plugin.Outcome;
import org.easyb.plugin.StepResult;

public class StubResultNode implements ResultNode<StubResultNode> {
    private StepResult result;
    private StubResultNode child;
    private String output = "";

    public StubResultNode(StepResult result) {
        this.result = result;
    }

    public StepResult getResult() {
        return result;
    }

    public void add(StubResultNode child) {
        this.child = child;
    }

    public StubResultNode getChild() {
        return child;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
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
