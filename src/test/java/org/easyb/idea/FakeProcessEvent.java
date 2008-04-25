/*
 * Copyright (c) 2007
 * Semantra, Inc. Addison, Texas 214.445.2900
 */
package org.easyb.idea;

import java.io.OutputStream;

import com.intellij.execution.process.ProcessEvent;

class FakeProcessEvent extends ProcessEvent {
    private String text;

    public FakeProcessEvent(String text) {
        super(new FakeProcessHandler());
        this.text = text;
    }

    public String getText() {
        return text;
    }

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