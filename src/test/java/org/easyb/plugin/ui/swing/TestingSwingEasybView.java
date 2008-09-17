package org.easyb.plugin.ui.swing;

import org.easyb.plugin.ui.ViewEventListener;

import javax.swing.tree.TreePath;

public class TestingSwingEasybView extends SwingEasybView {
    public TestingSwingEasybView(final ViewEventListener listener) {
        super(listener);
    }

    public void selectNode(SwingResultNode node) {
        tree.setSelectionPath(new TreePath(node.getPath()));
    }
}
