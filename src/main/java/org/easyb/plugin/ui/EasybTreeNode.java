package org.easyb.plugin.ui;

import javax.swing.tree.DefaultMutableTreeNode;

public class EasybTreeNode extends DefaultMutableTreeNode {
    public EasybTreeNode(Object userObject) {
        super(userObject);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(getUserObject()).append("[");
        for (int i = 0; i < getChildCount(); i++) {
            builder.append(getChildAt(i)).append(" ");
        }
        builder.append("]");

        return builder.toString();
    }
}
