package org.easyb.plugin.ui;

import org.easyb.plugin.ui.swing.SwingNodeBuilder;
import org.easyb.plugin.ui.swing.SwingResultNode;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.Outcome;
import static org.easymock.EasyMock.*;
import org.junit.Test;
import org.disco.easyb.util.BehaviorStepType;

public class WhenResultNodeSelected {
    private static final String TEST_OUTPUT = "test output";

    @SuppressWarnings("unchecked")
    @Test
    public void shouldDisplayNodeOutput() {
        EasybView<SwingResultNode> view = createMock(EasybView.class);

        view.writeOutput(TEST_OUTPUT);

        replay(view);

        EasybPresenter presenter = new EasybPresenter<SwingResultNode>(view, new SwingNodeBuilder());
        ResultNode node = new StubResultNode(new StepResult("name", BehaviorStepType.THEN, Outcome.SUCCESS));
        node.setOutput(TEST_OUTPUT);
        presenter.resultSelected(node);

        verify(view);
    }
}
