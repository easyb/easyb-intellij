package org.easyb.plugin.ui.swing;

import static org.disco.easyb.util.BehaviorStepType.THEN;
import static org.easyb.plugin.Outcome.SUCCESS;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.ViewEventListener;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.replay;
import org.junit.Test;

public class WhenSwingNodeSelected {
    @Test
    public void shouldNotfyPresenter()
    {
        SwingNodeBuilder builder = new SwingNodeBuilder();
        StepResult result = new StepResult("name", THEN, SUCCESS);
        SwingResultNode node = builder.build(result);

        ViewEventListener listener = createMock(ViewEventListener.class);
        listener.resultSelected(node);
        replay(listener);

        TestingSwingEasybView view = new TestingSwingEasybView();
        view.registerEventListener(listener);
        view.addBehaviorResult(node);
        view.selectNode(node);

        verify(listener);
    }
}
