package org.easyb.plugin.ui;

import org.easyb.plugin.SpecEventListener;
import org.easyb.plugin.event.EasybEvent;

public class EasybPresenter implements SpecEventListener {
    private EasybView view;

    public EasybPresenter(EasybView view) {
        this.view = view;
    }

    public void eventFired(EasybEvent event) {
        view.addSpecResult(event.toString());
    }
}
