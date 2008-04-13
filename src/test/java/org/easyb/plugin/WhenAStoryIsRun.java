package org.easyb.plugin;

import javax.swing.tree.DefaultMutableTreeNode;

import static junit.framework.Assert.assertEquals;
import org.disco.easyb.BehaviorStep;
import org.disco.easyb.domain.Story;
import org.disco.easyb.listener.ExecutionListener;
import static org.disco.easyb.util.BehaviorStepType.SCENARIO;
import org.easyb.plugin.ui.EasybPresenter;
import org.easyb.plugin.ui.EasybView;
import org.junit.Test;

public class WhenAStoryIsRun {
    @Test
    public void shouldAddNodesToTree() {
        DefaultMutableTreeNode storyNode = nodeFor("Transferring funds", RunResult.SUCCESS);
        DefaultMutableTreeNode scenarioNode = nodeFor("Amount exceeds available funds", RunResult.SUCCESS);
        storyNode.add(scenarioNode);

        StubView view = new StubView();

        ExecutionListener presenter = new EasybPresenter(view);
        presenter.startBehavior(new Story("Transferring funds", null));
        presenter.startStep(new BehaviorStep(SCENARIO, "Amount exceeds available funds"));

        DefaultMutableTreeNode rootNode = view.getResultNode();
        assertEquals(storyNode.getUserObject(), rootNode.getUserObject());
        assertEquals(scenarioNode.getUserObject(), childAt(rootNode, 0).getUserObject());
    }

    private DefaultMutableTreeNode childAt(DefaultMutableTreeNode rootNode, int index) {
        return ((DefaultMutableTreeNode) rootNode.getChildAt(index));
    }

    private DefaultMutableTreeNode nodeFor(String phrase, RunResult result) {
        return new DefaultMutableTreeNode(new StepResult(phrase, result));
    }

    private static class StubView implements EasybView {
        private DefaultMutableTreeNode resultNode;

        public void addBehaviorResult(DefaultMutableTreeNode resultNode) {
            this.resultNode = resultNode;
        }

        public DefaultMutableTreeNode getResultNode() {
            return resultNode;
        }
    }
}
