package org.easyb.plugin.ui;

public interface EasybView<T extends ResultNode> {
    void addBehaviorResult(T result);

    void addBehaviorResult(T parent, T result);

    void writeOutput(String text);

    void refresh();
}
