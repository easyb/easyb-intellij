package org.easyb.plugin.ui;

import org.easyb.plugin.ui.swing.EasybTreeNode;

public interface EasybView {
    void addBehaviorResult(EasybTreeNode result);

    void addBehaviorResult(EasybTreeNode parent, EasybTreeNode result);

    void writeOutput(String text);

    void refresh();
}
