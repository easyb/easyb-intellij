package org.easyb.components.runner;

import java.io.OutputStream;

import com.intellij.execution.process.ProcessHandler;

public class EasybProcessHandler extends ProcessHandler {
    public void startNotify() {
        addProcessListener(new EasybRunner(this));
        super.startNotify();
    }

    public void testingComplete() {
        notifyProcessTerminated(0);
    }

    protected void destroyProcessImpl() {
    }

    protected void detachProcessImpl() {
        notifyProcessDetached();
    }

    public boolean detachIsDefault() {
        return false;
    }

    public OutputStream getProcessInput() {
        return null;
    }
}
