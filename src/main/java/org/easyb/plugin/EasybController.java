package org.easyb.plugin;

public class EasybController implements SpecEventListener {
    private EasybView view;

    public EasybController(EasybView view) {
        this.view = view;
    }

    public void specPassed() {
        view.addSpecResult();
    }
}
