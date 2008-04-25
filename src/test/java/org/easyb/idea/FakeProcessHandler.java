/*
 * Copyright (c) 2007
 * Semantra, Inc. Addison, Texas 214.445.2900
 */
package org.easyb.idea;

import java.io.OutputStream;

import com.intellij.execution.process.ProcessHandler;

public class FakeProcessHandler extends ProcessHandler {
    protected void destroyProcessImpl() {
    }

    protected void detachProcessImpl() {
    }

    public boolean detachIsDefault() {
        return false;
    }

    public OutputStream getProcessInput() {
        return null;
    }
}
