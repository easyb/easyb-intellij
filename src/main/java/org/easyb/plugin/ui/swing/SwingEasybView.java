package org.easyb.plugin.ui.swing;

import static org.disco.easyb.util.BehaviorStepType.GENESIS;
import static org.easyb.plugin.Outcome.RUNNING;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.EasybView;
import org.easyb.plugin.ui.ViewEventListener;

import javax.swing.*;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class SwingEasybView extends JPanel implements EasybView<SwingResultNode> {
    private JTextArea consoleTextArea;
    private SwingResultNode root;
    protected JTree tree;

    public SwingEasybView(final ViewEventListener viewListener) {
        setLayout(new BorderLayout());

        root = new SwingResultNode(new StepResult("Root", GENESIS, RUNNING));
        tree = createTree(root);
        tree.setCellRenderer(new EasybNodeRenderer());
        tree.setRootVisible(false);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {
                SwingResultNode node = (SwingResultNode) tree.getLastSelectedPathComponent();
                viewListener.resultSelected(node.getResult());
            }
        });

        JTextArea outputTextArea = new JTextArea();
        consoleTextArea = new JTextArea();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Output", new JScrollPane(outputTextArea));
        tabbedPane.addTab("Console", new JScrollPane(consoleTextArea));

        JSplitPane pane = new JSplitPane(HORIZONTAL_SPLIT, new JScrollPane(tree), tabbedPane);
        pane.setDividerLocation(300);
        add(pane);
    }

    protected JTree createTree(SwingResultNode node) {
        return new JTree(node);
    }

    public void addBehaviorResult(SwingResultNode result) {
        addBehaviorResult(root, result);
    }

    public void addBehaviorResult(SwingResultNode parent, SwingResultNode result) {
        getModel().insertNodeInto(result, parent, parent.getChildCount());
        refresh();
    }

    public void displayFailure(Throwable failure) {
    }

    public void writeConsole(String text) {
        consoleTextArea.append(text);
    }

    public void refresh() {
        getModel().nodeStructureChanged(root);
        TreeUtil.expandAll(tree, true);
    }

    private DefaultTreeModel getModel() {
        return ((DefaultTreeModel) tree.getModel());
    }
}
