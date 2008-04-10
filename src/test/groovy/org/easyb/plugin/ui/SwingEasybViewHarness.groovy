package org.easyb.plugin.ui

import javax.swing.JFrame

class SwingEasybViewHarness {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        SwingEasybView view = new SwingEasybView()
        view.addSpecResult("Scenario A");
        view.addSpecResult("Scenario B");
        view.addSpecResult("Scenario C");
        frame.add(view);
        frame.show();
    }
}