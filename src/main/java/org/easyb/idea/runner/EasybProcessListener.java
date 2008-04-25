/*
 * Copyright (c) 2007
 * Semantra, Inc. Addison, Texas 214.445.2900
 */
package org.easyb.idea.runner;

import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.openapi.util.Key;
import org.easyb.plugin.ConsoleOutputListener;

public class EasybProcessListener implements ProcessListener {
    private ConsoleOutputListener consoleListener;

    public EasybProcessListener(ConsoleOutputListener consoleListener) {
        this.consoleListener = consoleListener;
    }

    public void startNotified(ProcessEvent event) {
    }

    public void processTerminated(ProcessEvent event) {
    }

    public void processWillTerminate(ProcessEvent event, boolean willBeDestroyed) {
    }

    public void onTextAvailable(ProcessEvent event, Key outputType) {
        consoleListener.textAvailable(event.getText());
    }
}
