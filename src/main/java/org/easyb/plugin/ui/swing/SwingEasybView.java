package org.easyb.plugin.ui.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import static org.disco.easyb.util.BehaviorStepType.GENESIS;
import static org.easyb.plugin.RunResult.INFORMATIONAL;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.EasybView;

public class SwingEasybView extends JPanel implements EasybView {
    private DefaultMutableTreeNode root;
    JTree tree;

    public SwingEasybView() {
        setLayout(new BorderLayout());

        root = new EasybTreeNode(new StepResult("Root", GENESIS, INFORMATIONAL));
        tree = new JTree(root);
        tree.setCellRenderer(new EasybNodeRenderer());
        tree.setRootVisible(false);

        add(new JScrollPane(tree), BorderLayout.CENTER);
    }

    public void addBehaviorResult(EasybTreeNode resultNode) {
        ((DefaultTreeModel) tree.getModel()).insertNodeInto(resultNode, root, root.getChildCount());
        TreeUtil.expandAll(tree, true);
    }
}
