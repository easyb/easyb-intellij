package org.easyb.plugin;

import org.easyb.plugin.ui.EasybPresenter;
import org.easyb.plugin.ui.SwingEasybView;

public class FakeEasybBuilder {
    private static SwingEasybView view = new SwingEasybView();
    private static EasybPresenter presenter = new EasybPresenter(view);
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

        pluginRunner = new EasybPluginRunner(fakeEasybRunner, presenter);
    }

    public static SwingEasybView getView() {
        return view;
    }

    public static EasybPresenter getPresenter() {
        return presenter;
    }

    public static EasybPluginRunner getPluginRunner() {
        return pluginRunner;
    }
}
