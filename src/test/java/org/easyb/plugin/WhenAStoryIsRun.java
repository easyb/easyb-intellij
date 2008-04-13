package org.easyb.plugin;

import static junit.framework.Assert.assertEquals;
import org.disco.easyb.BehaviorStep;
import org.disco.easyb.util.BehaviorStepType;
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
        EasybTreeNode scenarioNode = nodeFor(SCENARIO, "Amount exceeds available funds");
        scenarioNode.add(nodeFor(GIVEN, "An account balance of $100"));
        scenarioNode.add(nodeFor(WHEN, "A transfer of $150 is requested"));
        scenarioNode.add(nodeFor(THEN, "The request should be rejected"));

        EasybTreeNode storyNode = nodeFor(STORY, "Transferring funds");
        storyNode.add(scenarioNode);

        presenter.startStep(new BehaviorStep(STORY, "Transferring funds"));
        presenter.startStep(new BehaviorStep(SCENARIO, "Amount exceeds available funds"));
        presenter.startStep(new BehaviorStep(GIVEN, "An account balance of $100"));
        presenter.stopStep();
        presenter.startStep(new BehaviorStep(WHEN, "A transfer of $150 is requested"));
        presenter.stopStep();
        presenter.startStep(new BehaviorStep(THEN, "The request should be rejected"));
        presenter.stopStep();
        presenter.stopStep();
        presenter.stopStep();

        assertEquals(storyNode, view.getResultNode());
    }

    private EasybTreeNode nodeFor(BehaviorStepType type, String phrase) {
        return new EasybTreeNode(new StepResult(phrase, type, RunResult.SUCCESS));
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
