package org.easyb.plugin.ui.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.easyb.plugin.StepResult;

public class EasybNodeRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        EasybTreeNode node = (EasybTreeNode) value;
        StepResult stepResult = (StepResult) node.getUserObject();

        String stepName = capitalizeName(stepResult.getStepType().toString());

        label.setText(stepName + " " + stepResult.getStepName());
        label.setIcon(loadIcon(node));

        return label;
    }

    private String capitalizeName(String word) {
        char[] letters = word.toLowerCase().toCharArray();

        StringBuilder builder = new StringBuilder();
        builder.append(Character.toUpperCase(letters[0]));
        for (int i = 1; i < letters.length; i++) {
            builder.append(letters[i]);
        }

        return builder.toString();
    }

    private static Icon loadIcon(EasybTreeNode node) {
        switch (node.getResult().getOutcome()) {
            case FAILURE:
                return new ImageIcon(EasybNodeRenderer.class.getResource("/failure.png"));
            case PENDING:
                return new ImageIcon(EasybNodeRenderer.class.getResource("/pending.png"));
            default:
                return new ImageIcon(EasybNodeRenderer.class.getResource("/success.png"));
        }
    }
}
