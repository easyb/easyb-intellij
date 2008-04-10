package org.easyb.plugin;

import org.easyb.plugin.event.EasybEvent;

public class EasybController implements SpecEventListener {
    private EasybView view;

    public EasybController(EasybView view) {
        this.view = view;
    }

    public void eventFired(EasybEvent event) {
        view.addSpecResult(event.toString());
    }
}
