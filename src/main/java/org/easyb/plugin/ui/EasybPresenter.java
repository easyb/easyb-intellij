package org.easyb.plugin.ui;

import org.disco.easyb.BehaviorStep;
import org.disco.easyb.domain.Behavior;
import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.result.Result;
import static org.disco.easyb.util.BehaviorStepType.STORY;
import static org.easyb.plugin.Outcome.*;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.swing.EasybTreeNode;
import org.easyb.plugin.ui.swing.EasybTreeNodeStack;

public class EasybPresenter implements ExecutionListener {
    private EasybView view;
    private EasybTreeNodeStack nodeStack;
    private boolean descendantFailed = false;

    public EasybPresenter(EasybView view) {
        this.view = view;
        nodeStack = new EasybTreeNodeStack();
    }

    public void startBehavior(Behavior behavior) {
        descendantFailed = false;
    }

    public void startStep(BehaviorStep behaviorStep) {
        EasybTreeNode node = new EasybTreeNode(new StepResult(behaviorStep.name, behaviorStep.stepType, RUNNING));
        if (behaviorStep.stepType == STORY) {
            view.addBehaviorResult(node);
        } else {
            nodeStack.peek().add(node);
            view.refresh();
        }
        nodeStack.push(node);
    }

    public void describeStep(String s) {
    }

    public void gotResult(Result result) {
        StepResult stepResult = nodeStack.peek().getResult();
        if (descendantFailed) {
            stepResult.setOutcome(FAILURE);
        } else {
            stepResult.setOutcome(outcomeForResult(result));
        }
        if (result.failed()) {
            descendantFailed = true;
        }
        view.refresh();
    }

    public void stopStep() {
        StepResult stepResult = nodeStack.peek().getResult();
        if (stepResult.getOutcome() == RUNNING) {
            if (descendantFailed) {
                stepResult.setOutcome(FAILURE);
            } else {
                stepResult.setOutcome(SUCCESS);
            }
        }
        nodeStack.pop();
        view.refresh();
    }

    public void stopBehavior(Behavior behavior) {
    }

    public void completeTesting() {
    }
}