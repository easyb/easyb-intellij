package org.easyb.plugin;

import org.easyb.plugin.ui.EasybController;
import org.easyb.plugin.ui.SwingEasybView;

public class FakeEasybBuilder {
    private static SwingEasybView view = new SwingEasybView();
    private static EasybController controller = new EasybController(view);
    private static EasybPluginRunner pluginRunner;

    static {
        EasybRunner fakeEasybRunner = new EasybRunner() {
            public SpecificationResults executeSpec(String spec) {
                SpecificationResults results = new SpecificationResults();
                results.addResult(new SpecResult("Scenario A"));
                results.addResult(new SpecResult("Scenario B"));
                results.addResult(new SpecResult("Scenario C"));
                return results;
            }
        };

        pluginRunner = new EasybPluginRunner(fakeEasybRunner, controller);
    }

    public static SwingEasybView getView() {
        return view;
    }

    public static EasybController getController() {
        return controller;
    }

    public static EasybPluginRunner getPluginRunner() {
        return pluginRunner;
    }
}
