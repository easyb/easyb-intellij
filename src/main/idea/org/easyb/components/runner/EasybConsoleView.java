package org.easyb.components.runner;

import javax.swing.*;

import org.easyb.plugin.ui.swing.SwingEasybView;

public class EasybConsoleView extends ConsoleViewAdapter{
    private SwingEasybView view;

    public EasybConsoleView(SwingEasybView view) {
        this.view = view;
    }

    public JComponent getComponent() {
        return view;
    }
}
