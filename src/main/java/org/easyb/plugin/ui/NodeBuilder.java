package org.easyb.plugin.ui;

import org.easyb.plugin.StepResult;

public interface NodeBuilder <T extends ResultNode> {
    T build(StepResult result);
}
