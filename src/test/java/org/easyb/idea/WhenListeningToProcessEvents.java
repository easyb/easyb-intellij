/*
 * Copyright (c) 2007
 * Semantra, Inc. Addison, Texas 214.445.2900
 */
package org.easyb.idea;

import com.intellij.execution.process.ProcessListener;
import org.easyb.idea.runner.EasybProcessListener;
import org.easyb.plugin.ConsoleOutputListener;
import static org.easymock.EasyMock.*;
import org.junit.Test;

public class WhenListeningToProcessEvents {
    private static final String TEXT = "expected output";

    @Test
    public void shouldNotifyConsoleListener() {
        ConsoleOutputListener consoleListener = createMock(ConsoleOutputListener.class);
        consoleListener.textAvailable(TEXT);
        expectLastCall();
        replay(consoleListener);

        ProcessListener listener = new EasybProcessListener(consoleListener);
        listener.onTextAvailable(new FakeProcessEvent(TEXT), null);

        verify(consoleListener);
    }
}