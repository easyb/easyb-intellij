package org.easyb.plugin.ui.swing;

import java.util.ArrayList;

public class EasybTreeNodeStack extends ArrayList<EasybTreeNode> {
    public void push(EasybTreeNode node) {
        add(node);
    }

    public EasybTreeNode pop() {
        return remove(size() - 1);
    }

    public EasybTreeNode peek() {
        return get(size() - 1);
    }
}
