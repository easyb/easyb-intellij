package org.easyb.plugin.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.easyb.plugin.EasybView;

public class DefaultEasybView extends JPanel implements EasybView {
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    private JTree tree;

    public DefaultEasybView() {
        setLayout(new BorderLayout());

        root = new DefaultMutableTreeNode();
        model = new DefaultTreeModel(root);

        tree = new JTree(root);
        tree.setRootVisible(false);

        add(tree, BorderLayout.CENTER);
    }

    public void addSpecResult() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Spec passed!");
        model.insertNodeInto(node, root, root.getChildCount());
        tree.scrollPathToVisible(new TreePath(node.getPath()));
    }
}
