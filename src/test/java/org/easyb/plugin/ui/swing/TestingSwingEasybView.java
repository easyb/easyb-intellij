package org.easyb.plugin.ui.swing;

import javax.swing.tree.TreePath;

public class TestingSwingEasybView extends SwingEasybView {
    public void selectNode(SwingResultNode node) {
        tree.setSelectionPath(new TreePath(node.getPath()));
    }
}
