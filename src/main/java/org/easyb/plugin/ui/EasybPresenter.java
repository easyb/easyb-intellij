package org.easyb.plugin.ui;

import java.util.Stack;

import org.disco.easyb.BehaviorStep;
import org.disco.easyb.domain.Behavior;
import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.result.Result;
import static org.disco.easyb.util.BehaviorStepType.SPECIFICATION;
import static org.disco.easyb.util.BehaviorStepType.STORY;
import org.easyb.plugin.ConsoleOutputListener;
import static org.easyb.plugin.Outcome.*;
import org.easyb.plugin.StepResult;

public class EasybPresenter<T extends ResultNode>
        implements ExecutionListener, ConsoleOutputListener, ViewEventListener {
    private EasybView<T> view;
    private NodeBuilder<T> nodeBuilder;
    private Stack<T> nodeStack;
    private boolean descendantFailed = false;

    public EasybPresenter(EasybView<T> view, NodeBuilder<T> nodeBuilder) {
        this.view = view;
        this.nodeBuilder = nodeBuilder;
        nodeStack = new Stack<T>();
    }

    public void startBehavior(Behavior behavior) {
        descendantFailed = false;
    }

    public void startStep(BehaviorStep behaviorStep) {
        T node = nodeBuilder.build(new StepResult(behaviorStep.getName(), behaviorStep.getStepType(), RUNNING));
        if (behaviorStep.getStepType() == STORY || behaviorStep.getStepType() == SPECIFICATION) {
            view.addBehaviorResult(node);
        } else {
            view.addBehaviorResult(nodeStack.peek(), node);
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
            stepResult.setCause(result.cause);
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

    public void stopBehavior(BehaviorStep behaviorStep, Behavior behavior) {
    }

    public void completeTesting() {
    }

    public void textAvailable(String text) {
        view.writeConsole(text);
    }

    public void resultSelected(StepResult result) {
    }
}