package org.easyb.plugin.ui;

import java.util.HashMap;
import java.util.Map;
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
    private Map<String, T> nodeMap;
    private boolean descendantFailed = false;
    private String lastSpecRunName;

    private SpecificationRunningParser specRunningParser = new SpecificationRunningParser();

    public EasybPresenter(EasybView<T> view, NodeBuilder<T> nodeBuilder) {
        this.view = view;
        this.nodeBuilder = nodeBuilder;
        nodeStack = new Stack<T>();
        nodeMap = new HashMap<String, T>();
    }

    public void startBehavior(Behavior behavior) {
        descendantFailed = false;
    }

    public void startStep(BehaviorStep behaviorStep) {
        T node = buildNode(behaviorStep);
        if (behaviorStep.getStepType() == STORY || behaviorStep.getStepType() == SPECIFICATION) {
            view.addBehaviorResult(node);
        } else {
            view.addBehaviorResult(currentNode(), node);
        }
        nodeStack.push(node);
    }

    private T buildNode(BehaviorStep behaviorStep) {
        T node = nodeBuilder.build(new StepResult(behaviorStep.getName(), behaviorStep.getStepType(), RUNNING));
        nodeMap.put(behaviorStep.getName(), node);
        return node;
    }

    public void describeStep(String s) {
    }

    public void gotResult(Result result) {
        StepResult stepResult = currentNode().getResult();
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
        StepResult stepResult = currentNode().getResult();
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

        if (specRunningParser.isSpecificationRunningMessage(text)) {
            captureSpecificationRunningMessage(text);
        } else {
            if (lastSpecRunName != null) {
                ResultNode lastSpecRunNode = nodeMap.get(lastSpecRunName);
                appendOutput(text, lastSpecRunNode);
            }
        }
    }

    private void appendOutput(String text, ResultNode lastSpecRunNode) {
        lastSpecRunNode.setOutput(lastSpecRunNode.getOutput() + text);
    }

    private void captureSpecificationRunningMessage(String text) {
        if (specRunningParser.isSpecificationRunningMessage(text)) {
            lastSpecRunName = specRunningParser.parseSpecNameFrom(text);
        }
    }

    public void resultSelected(ResultNode result) {
        view.writeOutput(result.getOutput());
    }

    private T currentNode() {
        return nodeStack.peek();
    }
}