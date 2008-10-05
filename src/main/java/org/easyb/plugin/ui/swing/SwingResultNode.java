package org.easyb.plugin.ui.swing;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.ResultNode;

public class SwingResultNode extends DefaultMutableTreeNode implements ResultNode<MutableTreeNode> {
    private StepResult result;
    private String output;

    public SwingResultNode(StepResult result) {
        super(result);
        this.result = result;
    }

    public StepResult getResult() {
        return result;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        SwingResultNode that = (SwingResultNode) other;

        if (getUserObject().equals(that.getUserObject())) {
            if (getChildCount() != that.getChildCount()) {
                return false;
            }
            for (int i = 0; i < getChildCount(); i++) {
                if (!getChildAt(i).equals(that.getChildAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return getUserObject().hashCode();
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
