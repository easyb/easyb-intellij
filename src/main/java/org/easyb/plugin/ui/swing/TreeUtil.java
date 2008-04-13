package org.easyb.plugin.ui.swing;

import java.util.Enumeration;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeNode;
import javax.swing.*;

/**
 * From Java Almanac (http://exampledepot.com/egs/javax.swing.tree/ExpandAll.html)
 */
public class TreeUtil {
    /**
     * If expand is true, expands all nodes in the tree.
     * Otherwise, collapses all nodes in the tree.
     * 
     * @param tree Tree to expand
     * @param expand True to expand, false to collapse
     */
    public static void expandAll(JTree tree, boolean expand) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();

        // Traverse tree from root
        expandAll(tree, new TreePath(root), expand);
    }

    private static void expandAll(JTree tree, TreePath parent, boolean expand) {
        // Traverse children
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }

        // Expansion or collapse must be done bottom-up
        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }
}
