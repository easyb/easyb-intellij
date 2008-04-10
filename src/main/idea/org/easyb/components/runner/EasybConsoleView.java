package org.easyb.components.runner;

import java.util.Collections;
import static java.util.Collections.singletonList;
import javax.swing.*;

import org.easyb.plugin.ui.SwingEasybView;
import org.easyb.plugin.FakeEasybBuilder;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.openapi.util.Key;

public class EasybConsoleView extends ConsoleViewAdapter implements ProcessListener {
    public void attachToProcess(ProcessHandler processHandler) {
        processHandler.addProcessListener(this);
    }

    public JComponent getComponent() {
        return FakeEasybBuilder.getView();
    }

    public void startNotified(ProcessEvent event) {
    }

    public void processTerminated(ProcessEvent event) {
        FakeEasybBuilder.getPluginRunner().executeSpecs(singletonList("Fake spec"));
    }

    public void processWillTerminate(ProcessEvent event, boolean willBeDestroyed) {
    }

    public void onTextAvailable(ProcessEvent event, Key outputType) {
    }
}
