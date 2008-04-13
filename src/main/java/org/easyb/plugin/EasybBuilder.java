package org.easyb.plugin;

import org.easyb.plugin.ui.EasybPresenter;
import org.easyb.plugin.ui.SwingEasybView;

public class EasybBuilder {
    private SwingEasybView view;
    private EasybPresenter presenter;

    public EasybBuilder() {
        view = new SwingEasybView();
        presenter = new EasybPresenter(view);
    }

    public SwingEasybView getView() {
        return view;
    }

    public EasybPresenter getPresenter() {
        return presenter;
    }
}
