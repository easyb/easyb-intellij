package org.easyb.plugin.ui;

import org.easyb.plugin.ConsoleOutputListener;
import org.easyb.plugin.Outcome;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.remoting.RemotableBehavior;
import org.easyb.plugin.remoting.RemotableBehaviorStep;
import org.easyb.plugin.remoting.RemotingExecutionListener;
import org.easyb.result.ReportingTag;
import org.easyb.result.Result;

import java.util.Stack;

import static org.easyb.plugin.Outcome.*;
import static org.easyb.util.BehaviorStepType.*;

public class EasybPresenter<T extends ResultNode>
  implements RemotingExecutionListener, ConsoleOutputListener, ViewEventListener {
  private EasybView<T> view;
  private NodeBuilder<T> nodeBuilder;
  private Stack<T> nodeStack;
  //private Map<String, T> nodeMap;
  private boolean descendantFailed = false;
  //private String lastSpecRunName;

  private SpecificationRunningParser specRunningParser = new SpecificationRunningParser();

  public EasybPresenter(EasybView<T> view, NodeBuilder<T> nodeBuilder) {
    this.view = view;
    this.nodeBuilder = nodeBuilder;
    nodeStack = new Stack<T>();
//        nodeMap = new HashMap<String, T>();
  }

  public void startBehavior(RemotableBehavior behavior) {
    descendantFailed = false;
  }

  public void startStep(RemotableBehaviorStep behaviorStep) {
    T node = buildNode(behaviorStep);
    if (behaviorStep.getStepType() == STORY || behaviorStep.getStepType() == SPECIFICATION) {
      view.addBehaviorResult(node);
    } else {
      view.addBehaviorResult(currentNode(), node);
    }
    nodeStack.push(node);
  }

  private T buildNode(RemotableBehaviorStep behaviorStep) {
    T node = nodeBuilder.build(new StepResult(behaviorStep.getName(), behaviorStep.getStepType(), RUNNING, behaviorStep.getId()));
//        nodeMap.put(behaviorStep.getName(), node);
    return node;
  }

  public void describeStep(String s) {
  }


  public void gotResult(Result result) {
    StepResult stepResult = currentNode().getResult();

    stepResult.setOutcome(outcomeForResult(result));

    if (result.failed()) {
      stepResult.setCause(result.cause);
      descendantFailed = true;
    }
    view.refresh();
  }

  public void stopStep() {
    StepResult stepResult = currentNode().getResult();

    if (descendantFailed && ( stepResult.getStepType() == EXECUTE || stepResult.getStepType() == STORY || stepResult.getStepType() == SPECIFICATION )) {
      stepResult.setOutcome(Outcome.FAILURE);
    } else if (stepResult.getOutcome() == RUNNING) {
      stepResult.setOutcome(SUCCESS);
    }

    nodeStack.pop();
    view.refresh();
  }

  public void stopBehavior(RemotableBehaviorStep behaviorStep, RemotableBehavior behavior) {
  }

  public void tag(ReportingTag tag) {

  }

  public void completeTesting() {
  }

  public void textAvailable(String text) {
    view.writeConsole(text);

    if (specRunningParser.isSpecificationRunningMessage(text)) {
      captureSpecificationRunningMessage(text);
    } else {
//            if (lastSpecRunName != null) {
//                ResultNode lastSpecRunNode = nodeMap.get(lastSpecRunName);
//                appendOutput(text, lastSpecRunNode);
//            }
    }
  }

  private void appendOutput(String text, ResultNode lastSpecRunNode) {
    lastSpecRunNode.setOutput(lastSpecRunNode.getOutput() + text);
  }

  private void captureSpecificationRunningMessage(String text) {
//        if (specRunningParser.isSpecificationRunningMessage(text)) {
//            lastSpecRunName = specRunningParser.parseSpecNameFrom(text);
//        }
  }

  public void resultSelected(ResultNode result) {
    if (result == null || result.getOutput() == null) {
      view.writeConsole("No info available for node\n");
    } else if (result.getOutput() == null) {
      view.writeConsole("No extra info available for " + result.getResult().getStepName());
    } else {
      view.writeOutput(result.getOutput());
    }
  }

  private T currentNode() {
    return nodeStack.peek();
  }
}
