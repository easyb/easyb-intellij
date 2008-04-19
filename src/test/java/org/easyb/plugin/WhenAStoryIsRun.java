package org.easyb.plugin;

import static junit.framework.Assert.assertEquals;
import org.disco.easyb.BehaviorStep;
import org.disco.easyb.result.Result;
import static org.disco.easyb.result.Result.FAILED;
import static org.disco.easyb.result.Result.SUCCEEDED;
import org.disco.easyb.util.BehaviorStepType;
import static org.disco.easyb.util.BehaviorStepType.*;
import static org.easyb.plugin.Outcome.FAILURE;
import static org.easyb.plugin.Outcome.SUCCESS;
import org.easyb.plugin.ui.EasybPresenter;
import org.easyb.plugin.ui.EasybView;
import org.easyb.plugin.ui.swing.EasybTreeNode;
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
        EasybTreeNode scenarioNode = nodeFor(SCENARIO, "Amount exceeds available funds", FAILURE);
        scenarioNode.add(nodeFor(GIVEN, "An account balance of $100", SUCCESS));
        scenarioNode.add(nodeFor(WHEN, "A transfer of $150 is requested", SUCCESS));
        scenarioNode.add(nodeFor(THEN, "The request should be rejected", SUCCESS));
        scenarioNode.add(nodeFor(THEN, "No funds should be transferred", FAILURE));

        EasybTreeNode storyNode = nodeFor(STORY, "Transferring funds", FAILURE);
        storyNode.add(scenarioNode);

        presenter.startStep(new BehaviorStep(STORY, "Transferring funds"));
        presenter.startStep(new BehaviorStep(SCENARIO, "Amount exceeds available funds"));
        presenter.startStep(new BehaviorStep(GIVEN, "An account balance of $100"));
        presenter.stopStep();
        presenter.startStep(new BehaviorStep(WHEN, "A transfer of $150 is requested"));
        presenter.stopStep();
        presenter.startStep(new BehaviorStep(THEN, "The request should be rejected"));
        presenter.gotResult(new Result(SUCCEEDED));
        presenter.stopStep();
        presenter.startStep(new BehaviorStep(THEN, "No funds should be transferred"));
        presenter.gotResult(new Result(FAILED));
        presenter.stopStep();
        presenter.stopStep();
        presenter.stopStep();

        assertEquals(storyNode, view.getResultNode());
    }

    private EasybTreeNode nodeFor(BehaviorStepType type, String phrase, Outcome outcome) {
        return new EasybTreeNode(new StepResult(phrase, type, outcome));
    }

    private static class StubView implements EasybView {
        private EasybTreeNode resultNode;

        public void addBehaviorResult(EasybTreeNode resultNode) {
            this.resultNode = resultNode;
        }

        public void refresh() {
        }

        public EasybTreeNode getResultNode() {
            return resultNode;
        }
    }
}
