package org.easyb.plugin.ui.swing;

import org.easyb.plugin.ui.NodeBuilder;
import org.easyb.plugin.StepResult;

public class SwingNodeBuilder implements NodeBuilder<SwingResultNode> {
    public SwingResultNode build(StepResult result) {
        return new SwingResultNode(result);
    }
}
