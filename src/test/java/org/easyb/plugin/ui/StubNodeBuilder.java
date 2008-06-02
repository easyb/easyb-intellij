package org.easyb.plugin.ui;

import org.easyb.plugin.StepResult;

public class StubNodeBuilder implements NodeBuilder<StubResultNode> {
    public StubResultNode build(StepResult result) {
        return new StubResultNode(result);
    }
}
