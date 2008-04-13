package org.easyb.plugin.ui;

import javax.swing.tree.DefaultMutableTreeNode;

import org.disco.easyb.BehaviorStep;
import org.disco.easyb.domain.Behavior;
import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.result.Result;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.RunResult;

public class EasybPresenter implements ExecutionListener {
    private EasybView view;
    private DefaultMutableTreeNode root;

    public EasybPresenter(EasybView view) {
        this.view = view;
    }

    public void startBehavior(Behavior behavior) {
        root = new DefaultMutableTreeNode(new StepResult(behavior.getPhrase(), RunResult.SUCCESS));
        view.addBehaviorResult(root);
    }

    public void startStep(BehaviorStep behaviorStep) {
        root.add(new DefaultMutableTreeNode(new StepResult(behaviorStep.name, RunResult.SUCCESS)));
    }

    public void describeStep(String s) {
    }

    public void gotResult(Result result) {
    }

    public void stopStep() {
    }

    public void stopBehavior(BehaviorStep behaviorStep, Behavior behavior) {
    }

    public void completeTesting() {
    }
}
