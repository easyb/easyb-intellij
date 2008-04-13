package org.easyb.plugin;

import static junit.framework.Assert.assertEquals;
import org.disco.easyb.BehaviorStep;
import org.disco.easyb.domain.Story;
import static org.disco.easyb.util.BehaviorStepType.*;
import org.easyb.plugin.ui.EasybPresenter;
import org.easyb.plugin.ui.EasybTreeNode;
import org.easyb.plugin.ui.EasybView;
import org.junit.Before;
import org.junit.Test;

public class WhenAStoryIsRun {
    private StubView view;
    private EasybPresenter presenter;

    @Before
    public void setUp() {
        view = new StubView();
        presenter = new EasybPresenter(view);
    }

    @Test
    public void shouldAddNodesToTree() {
        EasybTreeNode scenarioNode = nodeFor("Amount exceeds available funds");
        scenarioNode.add(nodeFor("An account balance of $100"));
        scenarioNode.add(nodeFor("A transfer of $150 is requested"));
        scenarioNode.add(nodeFor("The request should be rejected"));

        EasybTreeNode storyNode = nodeFor("Transferring funds");
        storyNode.add(scenarioNode);

        presenter.startBehavior(new Story("Transferring funds", null));
        presenter.startStep(new BehaviorStep(SCENARIO, "Amount exceeds available funds"));
        presenter.startStep(new BehaviorStep(GIVEN, "An account balance of $100"));
        presenter.startStep(new BehaviorStep(WHEN, "A transfer of $150 is requested"));
        presenter.startStep(new BehaviorStep(THEN, "The request should be rejected"));

        assertEquals(storyNode, view.getResultNode());
    }

    private EasybTreeNode nodeFor(String phrase) {
        return new EasybTreeNode(new StepResult(phrase, RunResult.SUCCESS));
    }

    private static class StubView implements EasybView {
        private EasybTreeNode resultNode;

        public void addBehaviorResult(EasybTreeNode resultNode) {
            this.resultNode = resultNode;
        }

        public EasybTreeNode getResultNode() {
            return resultNode;
        }
    }
}
