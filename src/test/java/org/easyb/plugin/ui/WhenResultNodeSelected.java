package org.easyb.plugin.ui;

import org.easyb.plugin.ui.swing.SwingNodeBuilder;
import org.easyb.plugin.ui.swing.SwingResultNode;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.Outcome;
import org.junit.Test;
import org.easyb.util.BehaviorStepType;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

public class WhenResultNodeSelected {
  private static final String TEST_OUTPUT = "test output";

  @SuppressWarnings("unchecked")
  @Test
  public void shouldDisplayNodeOutput() {
    EasybView<SwingResultNode> view = mock(EasybView.class);

    EasybPresenter presenter = new EasybPresenter<SwingResultNode>(view, new SwingNodeBuilder());
    ResultNode node = new StubResultNode(new StepResult("name", BehaviorStepType.THEN, Outcome.SUCCESS, 1));
    node.setOutput(TEST_OUTPUT);
    presenter.resultSelected(node);

    Mockito.verify(view).writeOutput(eq(TEST_OUTPUT+"\n"));
  }
}
