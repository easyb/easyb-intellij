package org.easyb.plugin.ui;

import org.easyb.plugin.StepResult;

public interface ResultNode<T> {
    StepResult getResult();

    void add(T child);
}
