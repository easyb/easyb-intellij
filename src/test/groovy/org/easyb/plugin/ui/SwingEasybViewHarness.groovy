package org.easyb.plugin.ui

import javax.swing.JFrame
import groovy.ui.Console

class SwingEasybViewHarness {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        SwingEasybView view = new SwingEasybView()
        frame.add(view);
        frame.show();
        frame.setSize(300, 200);
        
        view.addSpecResult("Scenario A");
        view.addSpecResult("Scenario B");
        view.addSpecResult("Scenario C");

        Console console = new Console()
        console.setVariable('frame', frame)
        console.setVariable('view', view)
        console.run()
    }
}