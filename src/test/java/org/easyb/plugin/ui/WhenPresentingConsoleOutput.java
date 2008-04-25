/*
 * Copyright (c) 2007
 * Semantra, Inc. Addison, Texas 214.445.2900
 */
package org.easyb.plugin.ui;

import org.easyb.plugin.ConsoleOutputListener;
import static org.easymock.EasyMock.*;
import org.junit.Test;

public class WhenPresentingConsoleOutput {
    private static final String TEXT = "expected output";

    @Test
    public void shouldWriteOutputToView() {
        EasybView view = createMock(EasybView.class);
        view.writeOutput(TEXT);
        expectLastCall();
        replay(view);

        ConsoleOutputListener presenter = new EasybPresenter(view);
        presenter.textAvailable(TEXT);

        verify(view);
    }
}
