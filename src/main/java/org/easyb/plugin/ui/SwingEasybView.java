package org.easyb.plugin.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.easyb.plugin.ui.EasybView;
import static org.easyb.plugin.ui.TreeUtil.expandAll;

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

    public void addSpecResult(String message) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(message);
        ((DefaultTreeModel) tree.getModel()).insertNodeInto(node, root, root.getChildCount());
        TreeUtil.expandAll(tree, true);
    }
}
