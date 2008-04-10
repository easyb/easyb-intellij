package org.easyb.components.runner;

import javax.swing.*;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.openapi.actionSystem.AnAction;
import org.jetbrains.annotations.NotNull;

public class ConsoleViewAdapter implements ConsoleView {
    public void print(String s, ConsoleViewContentType contentType) {
    }

    public void clear() {
    }

    public void scrollTo(int offset) {
    }

    public void attachToProcess(ProcessHandler processHandler) {
    }

    public void setOutputPaused(boolean value) {
    }

    public boolean isOutputPaused() {
        return false;
    }

    public boolean hasDeferredOutput() {
        return false;
    }

    public void performWhenNoDeferredOutput(Runnable runnable) {
    }

    public void setHelpId(String helpId) {
    }

    public void addMessageFilter(Filter filter) {
    }

    public void printHyperlink(String hyperlinkText, HyperlinkInfo info) {
    }

    public int getContentSize() {
        return 0;
    }

    public boolean canPause() {
        return false;
    }

    @NotNull
    public AnAction[] createUpDownStacktraceActions() {
        return new AnAction[0];
    }

    public JComponent getComponent() {
        return null;
    }

    public JComponent getPreferredFocusableComponent() {
        return null;
    }

    public void dispose() {
    }
}
