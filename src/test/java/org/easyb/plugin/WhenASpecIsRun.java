package org.easyb.plugin;

import static junit.framework.Assert.assertEquals;
import org.disco.easyb.BehaviorStep;
import org.disco.easyb.result.Result;
import static org.disco.easyb.result.Result.FAILED;
import static org.disco.easyb.util.BehaviorStepType.IT;
import static org.disco.easyb.util.BehaviorStepType.SPECIFICATION;
import static org.easyb.plugin.Outcome.FAILURE;
import org.easyb.plugin.ui.EasybPresenter;
import org.easyb.plugin.ui.StubNodeBuilder;
import org.easyb.plugin.ui.StubResultNode;
import static org.easyb.plugin.ui.StubResultNode.nodeFor;
import org.junit.Before;
import org.junit.Test;

public class WhenASpecIsRun {
    private StubView view;
    private EasybPresenter presenter;

    @Before
    public void setUp() {
        view = new StubView();
        presenter = new EasybPresenter<StubResultNode>(view, new StubNodeBuilder());
    }

    @Test
    public void shouldAddNodesToTree() {
        StubResultNode specNode = nodeFor(SPECIFICATION, "transferring funds", FAILURE);
        specNode.add(nodeFor(IT, "should withdraw funds", FAILURE));

        presenter.startStep(new BehaviorStep(SPECIFICATION, "transferring funds"));
        presenter.startStep(new BehaviorStep(IT, "should withdraw funds"));
        presenter.gotResult(new Result(FAILED));
        presenter.stopStep();
        presenter.stopStep();

        assertEquals(specNode, view.getResultNode());
    }
}
