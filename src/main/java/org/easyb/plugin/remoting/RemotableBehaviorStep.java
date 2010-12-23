package org.easyb.plugin.remoting;

import org.easyb.BehaviorStep;
import org.easyb.StoryContext;
import org.easyb.util.BehaviorStepType;

import java.io.Serializable;

public class RemotableBehaviorStep implements Serializable {
  private final BehaviorStepType stepType;
  private final String name;
  private final int id;
  private final String source;
  private final int lineNo;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RemotableBehaviorStep)) return false;

    RemotableBehaviorStep that = (RemotableBehaviorStep) o;

    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (stepType != that.stepType) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = stepType != null ? stepType.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  private static StoryContext lastContext;
  private static int stepCounter = 1;

  public RemotableBehaviorStep(BehaviorStep step) {
    this.stepType = step.getStepType();
    this.id = step.getId();
    this.source = step.getSource();
    this.lineNo = step.getLineNo();

    if ( step.getStepType() == BehaviorStepType.SCENARIO && step.getStoryContext() != null &&
          step.getStoryContext().getExampleData() != null ) {

      if ( lastContext != step.getStoryContext() ) {
        lastContext = step.getStoryContext();
        stepCounter = 1;
      }

      this.name =  "(run " + (stepCounter++) + ") " + step.getName();
    } else
      this.name = step.getName();
  }

  /**
   * this is only used in tests
   * @param specification
   * @param s
   */
  public RemotableBehaviorStep(BehaviorStepType specification, String s) {
    this.stepType = specification;
    this.name = s;
    this.id = 0; // used for testing
    this.source = "";
    this.lineNo = 0;
  }

  public BehaviorStepType getStepType() {
    return stepType;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getSource() {
    return source;
  }

  public int getLineNo() {
    return lineNo;
  }
}
