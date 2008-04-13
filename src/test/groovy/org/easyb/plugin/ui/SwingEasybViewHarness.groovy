package org.easyb.plugin.ui

import javax.swing.JFrame
import groovy.ui.Console

class SwingEasybViewHarness {
    public static void main(String[] args) {
        SwingEasybView view = new SwingEasybView()
        JFrame frame = new JFrame();
        frame.add(view);
        frame.show();
        frame.setSize(300, 200);
        
        view.addBehaviorResult("Scenario A");
        view.addBehaviorResult("Scenario B");
        view.addBehaviorResult("Scenario C");

        Console console = new Console()
        console.setVariable('frame', frame)
        console.setVariable('view', view)
        console.run()
    }
}