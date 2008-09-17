package org.easyb.plugin;

import org.disco.easyb.util.BehaviorStepType;

public class StepResult {
    private String stepName;
    private BehaviorStepType stepType;
    private Outcome outcome;
    private Throwable cause;
    private String output = "default output";

    public StepResult(String stepName, BehaviorStepType stepType, Outcome outcome) {
        this.stepName = stepName;
        this.stepType = stepType;
        this.outcome = outcome;
    }

    public String getStepName() {
        return stepName;
    }

    public BehaviorStepType getStepType() {
        return stepType;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String toString() {
        return stepName + ":" + outcome;
    }

    @SuppressWarnings("RedundantIfStatement")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StepResult that = (StepResult) o;

        if (outcome != that.outcome) {
            return false;
        }
        if (stepName != null ? !stepName.equals(that.stepName) : that.stepName != null) {
            return false;
        }
        if (stepType != that.stepType) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = (stepName != null ? stepName.hashCode() : 0);
        result = 31 * result + (stepType != null ? stepType.hashCode() : 0);
        result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
        return result;
    }
}
