package org.easyb.plugin;

public class StepResult {
    private String stepName;
    private RunResult outcome;

    public StepResult(String stepName, RunResult outcome) {
        this.stepName = stepName;
        this.outcome = outcome;
    }

    public String getStepName() {
        return stepName;
    }

    public RunResult getOutcome() {
        return outcome;
    }

    public String toString() {
        return stepName + ":" + outcome;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StepResult that = (StepResult) o;

        return stepName.equals(that.stepName) && outcome == that.outcome;
    }

    public int hashCode() {
        int result;
        result = stepName.hashCode();
        result = 31 * result + this.outcome.hashCode();
        return result;
    }
}
