package org.easyb.plugin;

import org.easyb.plugin.ui.EasybView;
import org.easyb.plugin.ui.StubResultNode;

public class StubView implements EasybView<StubResultNode> {
    private StubResultNode resultNode;

    public void addBehaviorResult(StubResultNode resultNode) {
        this.resultNode = resultNode;
    }

    public void addBehaviorResult(StubResultNode parent, StubResultNode result) {
        parent.add(result);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void writeOutput(String text) {
    }

    public void refresh() {
    }

    public StubResultNode getResultNode() {
        return resultNode;
    }
}
