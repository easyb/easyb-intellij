package org.easyb.intellij.components.runner;

import javax.swing.*;

import org.easyb.plugin.ui.SwingEasybView;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.openapi.util.Key;

public class EasybConsoleView extends ConsoleViewAdapter implements ProcessListener {
    private SwingEasybView view;

    public void attachToProcess(ProcessHandler processHandler) {
        processHandler.addProcessListener(this);
    }

    public JComponent getComponent() {
        view = new SwingEasybView();
        return view;
    }

    public void startNotified(ProcessEvent event) {
    }

    public void processTerminated(ProcessEvent event) {
        view.addSpecResult("Bar");
        view.addSpecResult("Foo");
        view.addSpecResult("Baz");
    }

    public void processWillTerminate(ProcessEvent event, boolean willBeDestroyed) {
    }

    public void onTextAvailable(ProcessEvent event, Key outputType) {
    }
}
