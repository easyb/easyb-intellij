package org.easyb.components.runner;

import javax.swing.*;

import com.intellij.execution.ui.ExecutionConsole;
import org.easyb.plugin.ui.SwingEasybView;

public class EasybConsoleView implements ExecutionConsole {
    private SwingEasybView view;

    public EasybConsoleView(SwingEasybView view) {
        this.view = view;
    }

    public JComponent getComponent() {
        return view;
    }

    public JComponent getPreferredFocusableComponent() {
        return null;
    }

    public void dispose() {
    }
}
