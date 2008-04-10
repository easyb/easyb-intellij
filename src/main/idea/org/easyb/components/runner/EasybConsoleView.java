package org.easyb.components.runner;

import javax.swing.*;

import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessListener;
import com.intellij.openapi.util.Key;
import org.easyb.plugin.EasybBuilder;
import org.easyb.plugin.SpecResult;
import org.easyb.plugin.event.SpecResultEvent;

public class EasybConsoleView extends ConsoleViewAdapter implements ProcessListener {
    public void attachToProcess(ProcessHandler processHandler) {
        processHandler.addProcessListener(this);
    }

    public JComponent getComponent() {
        return EasybBuilder.getView();
    }

    public void startNotified(ProcessEvent event) {
    }

    public void processTerminated(ProcessEvent event) {
        EasybBuilder.getPresenter().eventFired(new SpecResultEvent(new SpecResult("pushing null onto stack")));
        EasybBuilder.getPresenter().eventFired(new SpecResultEvent(new SpecResult("pushing item onto empty stack")));
    }

    public void processWillTerminate(ProcessEvent event, boolean willBeDestroyed) {
    }

    public void onTextAvailable(ProcessEvent event, Key outputType) {
    }
}
