package org.easyb.plugin;

import org.easyb.plugin.ui.EasybPresenter;
import org.easyb.plugin.ui.SwingEasybView;

public class EasybBuilder {
    private static SwingEasybView view = new SwingEasybView();
    private static EasybPresenter presenter = new EasybPresenter(view);

    public static SwingEasybView getView() {
        return view;
    }

    public static EasybPresenter getPresenter() {
        return presenter;
    }
}
