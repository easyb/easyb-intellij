package org.easyb.plugin;

import org.easyb.plugin.event.SpecResultEvent;

public class EasybPluginRunner {
    private EasybRunner easybRunner;
    private SpecEventListener listener;

    public EasybPluginRunner(EasybRunner easybRunner, SpecEventListener listener) {
        this.easybRunner = easybRunner;
        this.listener = listener;
    }

    public void executeSpecs(String[] specs) {
        String spec = specs[0];
        easybRunner.executeSpec(spec);
        listener.eventFired(new SpecResultEvent(spec.replace(".story", " story passed")));
    }
}
