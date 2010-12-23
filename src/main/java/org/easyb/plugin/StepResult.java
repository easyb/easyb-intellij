package org.easyb.plugin;

import org.easyb.plugin.remoting.RemotableBehaviorStep;
import org.easyb.util.BehaviorStepType;

public class StepResult {
  private final String stepName;
  private final BehaviorStepType stepType;
  private Outcome outcome;
  private Throwable cause;
  private final int originalId;
  private final int id;
  private final String source;
  private final int lineNo;

  private static int idGenerator = 1;

  public StepResult(String stepName, BehaviorStepType stepType, Outcome outcome, int id) {
    this.stepName = stepName;
    this.stepType = stepType;
    this.outcome = outcome;
    this.originalId = id;

    this.id = idGenerator ++;
    this.source = null;
    this.lineNo = 0;
  }

  public StepResult(RemotableBehaviorStep step) {
    this.stepName = step.getName();
    this.stepType = step.getStepType();
    this.outcome = Outcome.RUNNING;
    this.originalId = step.getId();

    this.id = idGenerator ++;
    this.source = step.getSource();
    this.lineNo = step.getLineNo();
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

    if ( that.getId() != id )
      return false;

//    if (outcome != that.outcome) {
//      return false;
//    }
//    if (stepName != null ? !stepName.equals(that.stepName) : that.stepName != null) {
//      return false;
//    }
//    if (stepType != that.stepType) {
//      return false;
//    }

    return true;
  }

  public int hashCode() {
    int result;
    result = ( stepName != null ? stepName.hashCode() : 0 );
    result = 31 * result + ( stepType != null ? stepType.hashCode() : 0 );
    result = 31 * result + ( outcome != null ? outcome.hashCode() : 0 );
    return result;
  }

  public int getId() {
    return id;
  }

  public int getLineNo() {
    return lineNo;
  }

  public String getSource() {
    return source;
  }
}
