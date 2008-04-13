package org.easyb.plugin.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class SwingEasybView extends JPanel implements EasybView {
    private DefaultMutableTreeNode root;
    JTree tree;

    public SwingEasybView() {
        setLayout(new BorderLayout());

        root = new DefaultMutableTreeNode();
        tree = new JTree(root);
        tree.setRootVisible(false);

        add(new JScrollPane(tree), BorderLayout.CENTER);
    }

    public void addBehaviorResult(EasybTreeNode resultNode) {
        ((DefaultTreeModel) tree.getModel()).insertNodeInto(resultNode, root, root.getChildCount());
        TreeUtil.expandAll(tree, true);
    }
}
