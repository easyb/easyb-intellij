package org.easyb.plugin;

import org.easyb.plugin.ui.swing.EasybTreeNode;
import org.easyb.plugin.ui.EasybView;

public class StubView implements EasybView {
    private EasybTreeNode resultNode;

    public void addBehaviorResult(EasybTreeNode resultNode) {
        this.resultNode = resultNode;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void writeOutput(String text) {
    }

    public void refresh() {
    }

    public EasybTreeNode getResultNode() {
        return resultNode;
    }
}
