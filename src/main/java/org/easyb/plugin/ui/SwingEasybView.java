package org.easyb.plugin.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.easyb.plugin.EasybView;

public class SwingEasybView extends JPanel implements EasybView {
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    private JTree tree;

    public SwingEasybView() {
        setLayout(new BorderLayout());

        root = new DefaultMutableTreeNode();
        model = new DefaultTreeModel(root);

        tree = new JTree(root);
        tree.setRootVisible(false);

        add(tree, BorderLayout.CENTER);
    }

    public void addSpecResult(String message) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(message);
        model.insertNodeInto(node, root, root.getChildCount());
        tree.scrollPathToVisible(new TreePath(node.getPath()));
    }
}
