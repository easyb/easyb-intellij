package org.easyb.components.runner;

import java.io.OutputStream;

import com.intellij.execution.process.ProcessHandler;
import org.easyb.plugin.ui.EasybPresenter;

public class EasybProcessHandler extends ProcessHandler {
    private String specificationPath;
    private EasybPresenter presenter;

    public EasybProcessHandler(EasybPresenter presenter, String specificationPath) {
        this.presenter = presenter;
        this.specificationPath = specificationPath;
    }

    public void startNotify() {
        addProcessListener(new EasybRunner(this, presenter, specificationPath));
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
