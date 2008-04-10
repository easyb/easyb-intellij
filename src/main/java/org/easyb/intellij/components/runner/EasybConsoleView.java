package org.easyb.intellij.components.runner;

import javax.swing.*;

import org.easyb.plugin.swing.DefaultEasybView;

public class EasybConsoleView extends ConsoleViewAdapter {
    public JComponent getComponent() {
        DefaultEasybView view = new DefaultEasybView();
        view.addSpecResult();
        view.addSpecResult();
        view.addSpecResult();
        return view;
    }
}
