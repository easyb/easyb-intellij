package org.easyb.plugin.ui;

import org.easyb.plugin.StepResult;

public interface ResultNode<T> {
    StepResult getResult();

    String getOutput();

    void setOutput(String output);

    void add(T child);
}
