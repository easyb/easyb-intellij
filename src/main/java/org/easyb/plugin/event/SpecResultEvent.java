package org.easyb.plugin.event;

import org.easyb.plugin.StepResult;

public class SpecResultEvent implements EasybEvent {
    private StepResult result;

    public SpecResultEvent(StepResult result) {
        this.result = result;
    }

    public String toString() {
        return result.getStepName();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpecResultEvent event = (SpecResultEvent) o;

        return !(result != null ? !result.equals(event.result) : event.result != null);
    }

    public int hashCode() {
        return (result != null ? result.hashCode() : 0);
    }
}
