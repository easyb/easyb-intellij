package org.easyb.plugin;

public class EasybPluginRunner {
    private EasybRunner easybRunner;
    private SpecEventListener listener;

    public EasybPluginRunner(EasybRunner easybRunner, SpecEventListener listener) {
        this.easybRunner = easybRunner;
        this.listener = listener;
    }

    public void executeSpecs(String[] specs) {
        easybRunner.executeSpec(specs[0]);
        listener.specPassed();
    }
}
