package org.easyb.plugin.ui.swing;

import static org.easyb.util.BehaviorStepType.GIVEN;
import static org.easyb.plugin.Outcome.SUCCESS;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.NodeBuilder;
import org.easyb.plugin.ui.ResultNode;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class WhenBuildingSwingNode {
    @Test
    public void shouldConstructEasybTreeNode() {
        NodeBuilder<? extends ResultNode> builder = new SwingNodeBuilder();

        StepResult result = new StepResult("name", GIVEN, SUCCESS);
        ResultNode expected = new SwingResultNode(result);
        ResultNode actual = builder.build(result);

        assertEquals(expected, actual);
        assertEquals(result, actual.getResult());
    }
}
