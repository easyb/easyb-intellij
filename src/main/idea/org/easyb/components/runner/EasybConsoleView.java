package org.easyb.components.runner;

import javax.swing.*;

import com.intellij.execution.ui.ExecutionConsole;
import org.easyb.plugin.EasybBuilder;

public class EasybConsoleView implements ExecutionConsole {
    public JComponent getComponent() {
        return EasybBuilder.getView();
    }

    public JComponent getPreferredFocusableComponent() {
        return null;
    }

    public void dispose() {
    }
}
