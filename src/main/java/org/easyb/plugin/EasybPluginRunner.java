package org.easyb.plugin;

import java.util.List;

import org.easyb.plugin.event.SpecResultEvent;

public class EasybPluginRunner {
    private EasybRunner easybRunner;
    private SpecEventListener listener;

    public EasybPluginRunner(EasybRunner easybRunner, SpecEventListener listener) {
        this.easybRunner = easybRunner;
        this.listener = listener;
    }

    public void executeSpecs(List<String> specs) {
        String spec = specs.get(0);
        for (SpecResult result : easybRunner.executeSpec(spec)) {
            listener.eventFired(new SpecResultEvent(result));
        }
    }
}
