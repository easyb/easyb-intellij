package org.easyb.plugin.ui;

import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private Pattern specPattern = Pattern.compile("Running (.*?) (specification|story).*\n");

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

        if (isSpecificationRunningMessage(text)) {
            captureSpecificationRunningMessage(text);
        } else {
            if (lastSpecRunName != null) {
                ResultNode lastSpecRunNode = nodeMap.get(lastSpecRunName);
                appendOutput(text, lastSpecRunNode);
            }
        }
    }

    private void appendOutput(String text, ResultNode lastSpecRunNode) {
        StepResult result = lastSpecRunNode.getResult();
        result.setOutput(result.getOutput() + text);
    }

    private boolean isSpecificationRunningMessage(String text) {
        return specPattern.matcher(text).matches();
    }

    private void captureSpecificationRunningMessage(String text) {
        Matcher specMatcher = specPattern.matcher(text);
        if (specMatcher.matches()) {
            lastSpecRunName = specMatcher.group(1);
        }
    }

    public void resultSelected(StepResult result) {
        view.writeOutput(result.getOutput());
    }

    private T currentNode() {
        return nodeStack.peek();
    }
}