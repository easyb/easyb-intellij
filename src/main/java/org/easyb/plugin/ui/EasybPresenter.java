package org.easyb.plugin.ui;

import org.disco.easyb.BehaviorStep;
import org.disco.easyb.domain.Behavior;
import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.result.Result;
import org.disco.easyb.util.BehaviorStepType;
import org.easyb.plugin.RunResult;
import org.easyb.plugin.StepResult;

public class EasybPresenter implements ExecutionListener {
    private EasybView view;
    private EasybTreeNode root;
    private EasybTreeNodeStack nodeStack;

    public EasybPresenter(EasybView view) {
        this.view = view;
        nodeStack = new EasybTreeNodeStack();
    }

    public void startBehavior(Behavior behavior) {
        root = new EasybTreeNode(new StepResult(behavior.getPhrase(), RunResult.SUCCESS));
        view.addBehaviorResult(root);
    }

    public void startStep(BehaviorStep behaviorStep) {
        EasybTreeNode node = new EasybTreeNode(new StepResult(behaviorStep.name, RunResult.SUCCESS));
        if (behaviorStep.stepType == BehaviorStepType.SCENARIO) {
            nodeStack.push(node);
            root.add(node);
        } else {
            nodeStack.peek().add(node);
        }
    }

    public void describeStep(String s) {
    }

    public void gotResult(Result result) {
    }

    public void stopStep() {
        nodeStack.pop();
    }

    public void stopBehavior(BehaviorStep behaviorStep, Behavior behavior) {
    }

    public void completeTesting() {
    }
}