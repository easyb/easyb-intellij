package org.easyb.plugin.ui;

import org.easyb.plugin.ui.swing.EasybTreeNode;

public interface EasybView {
    void addBehaviorResult(EasybTreeNode resultNode);
    void refresh();
}
