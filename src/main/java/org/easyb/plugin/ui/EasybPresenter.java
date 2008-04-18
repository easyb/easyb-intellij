package org.easyb.plugin.ui;

import org.disco.easyb.BehaviorStep;
import org.disco.easyb.domain.Behavior;
import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.result.Result;
import static org.disco.easyb.util.BehaviorStepType.STORY;
import org.easyb.plugin.RunResult;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.swing.EasybTreeNode;
import org.easyb.plugin.ui.swing.EasybTreeNodeStack;

public class EasybPresenter implements ExecutionListener {
    private EasybView view;
    private EasybTreeNodeStack nodeStack;

    public EasybPresenter(EasybView view) {
        this.view = view;
        nodeStack = new EasybTreeNodeStack();
    }

    public void startBehavior(Behavior behavior) {
    }

    public void startStep(BehaviorStep behaviorStep) {
        EasybTreeNode node = new EasybTreeNode(new StepResult(behaviorStep.name, behaviorStep.stepType, RunResult.SUCCESS));
        if (behaviorStep.stepType == STORY) {
            view.addBehaviorResult(node);
        } else {
            nodeStack.peek().add(node);
        }
        nodeStack.push(node);
    }

    public void describeStep(String s) {
    }

    public void gotResult(Result result) {
    }

    public void stopStep() {
        nodeStack.pop();
    }

    public void stopBehavior(Behavior behavior) {
    }

    public void completeTesting() {
    }
}