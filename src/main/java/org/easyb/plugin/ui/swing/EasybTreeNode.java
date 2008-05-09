package org.easyb.plugin.ui.swing;

import javax.swing.tree.DefaultMutableTreeNode;

import org.easyb.plugin.StepResult;
import org.easyb.plugin.Outcome;
import org.disco.easyb.util.BehaviorStepType;

public class EasybTreeNode extends DefaultMutableTreeNode {
    private StepResult result;

    public EasybTreeNode(StepResult result) {
        super(result);
        this.result = result;
    }

    public StepResult getResult() {
        return result;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        EasybTreeNode that = (EasybTreeNode) other;

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

    public static EasybTreeNode nodeFor(BehaviorStepType type, String phrase, Outcome outcome) {
        return new EasybTreeNode(new StepResult(phrase, type, outcome));
    }
}
