package org.easyb.components.runner;

import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import org.disco.easyb.domain.Specification;
import org.easyb.plugin.EasybBuilder;

public class EasybRunner extends ProcessAdapter implements Runnable {
    private EasybProcessHandler processHandler;

    public EasybRunner(EasybProcessHandler processHandler) {
        this.processHandler = processHandler;
    }

    public void run() {
        try {
            for (int i = 0; i < 4; i++) {
                EasybBuilder.getPresenter().startBehavior(new Specification("scenario", null));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processHandler.testingComplete();
    }

    public void startNotified(ProcessEvent event) {
        new Thread(this).start();
    }
}
