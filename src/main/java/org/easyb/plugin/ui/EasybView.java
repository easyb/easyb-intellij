package org.easyb.plugin.ui;

public interface EasybView<T extends ResultNode> {
    void addBehaviorResult(T result);

    void addBehaviorResult(T parent, T result);

    void displayFailure(Throwable failure);

    void writeConsole(String text);

    void refresh();
}
