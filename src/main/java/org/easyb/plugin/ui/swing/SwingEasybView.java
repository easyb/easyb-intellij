package org.easyb.plugin.ui.swing;

import java.awt.*;
import javax.swing.*;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import javax.swing.tree.DefaultTreeModel;

import static org.disco.easyb.util.BehaviorStepType.GENESIS;
import static org.easyb.plugin.Outcome.RUNNING;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.EasybView;

public class SwingEasybView extends JPanel implements EasybView<SwingResultNode> {
    private SwingResultNode root;
    private JTextArea outputTextArea;
    JTree tree;

    public SwingEasybView() {
        setLayout(new BorderLayout());

        root = new SwingResultNode(new StepResult("Root", GENESIS, RUNNING));
        tree = new JTree(root);
        tree.setCellRenderer(new EasybNodeRenderer());
        tree.setRootVisible(false);

        JTabbedPane tabbedPane = new JTabbedPane();
        outputTextArea = new JTextArea("Test");
        tabbedPane.addTab("Output", new JScrollPane(outputTextArea));

        JSplitPane pane = new JSplitPane(HORIZONTAL_SPLIT, new JScrollPane(tree), tabbedPane);
        pane.setDividerLocation(300);
        add(pane);
    }

    public void addBehaviorResult(SwingResultNode result) {
        addBehaviorResult(root, result);
    }

    public void addBehaviorResult(SwingResultNode parent, SwingResultNode result) {
        getModel().insertNodeInto(result, parent, parent.getChildCount());
        refresh();
    }

    public void writeOutput(String text) {
        outputTextArea.append(text);
    }

    public void refresh() {
        getModel().nodeStructureChanged(root);
        TreeUtil.expandAll(tree, true);
    }

    private DefaultTreeModel getModel() {
        return ((DefaultTreeModel) tree.getModel());
    }
}
