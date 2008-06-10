package org.easyb.plugin;

import org.easyb.plugin.ui.EasybView;
import org.easyb.plugin.ui.StubResultNode;

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

    @SuppressWarnings("UnusedDeclaration")
    public void writeConsole(String text) {
    }

    public void refresh() {
    }

    public StubResultNode getResultNode() {
        return resultNode;
    }

    public Throwable getFailure() {
        return failure;
    }
}
