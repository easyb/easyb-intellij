package org.easyb.plugin.ui;

public interface EasybView<T extends ResultNode> {
    void addBehaviorResult(T result);

    void addBehaviorResult(T parent, T result);

    void displayFailure(Throwable failure);

    void writeOutput(String text);

    void writeConsole(String text);

    void refresh();

    void registerEventListener(ViewEventListener listener);
}
