package org.easyb.plugin;

import org.easyb.plugin.ui.EasybView;
import org.easyb.plugin.ui.StubResultNode;
import org.easyb.plugin.ui.ViewEventListener;

public class StubView implements EasybView<StubResultNode> {
    private StubResultNode resultNode;
    private Throwable failure;

    public void addBehaviorResult(StubResultNode resultNode) {
        this.resultNode = resultNode;
    }

    public void addBehaviorResult(StubResultNode parent, StubResultNode result) {
        parent.add(result);
    }

    public void displayFailure(Throwable failure) {
        this.failure = failure;
    }

    public void writeOutput(String text) {
    }

    public void writeConsole(String text) {
    }

    public void refresh() {
    }

    public void registerEventListener(ViewEventListener listener) {
    }

    public StubResultNode getResultNode() {
        return resultNode;
    }

    public Throwable getFailure() {
        return failure;
    }
}
