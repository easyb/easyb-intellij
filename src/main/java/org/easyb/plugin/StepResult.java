package org.easyb.plugin;

import org.disco.easyb.util.BehaviorStepType;

public class StepResult {
    private String stepName;
    private BehaviorStepType stepType;
    private RunResult outcome;

    public StepResult(String stepName, BehaviorStepType stepType, RunResult outcome) {
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

    public RunResult getOutcome() {
        return outcome;
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
