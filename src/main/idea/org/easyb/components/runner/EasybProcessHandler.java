package org.easyb.components.runner;

import java.io.OutputStream;

import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import org.easyb.plugin.EasybBuilder;
import org.easyb.plugin.SpecResult;
import org.easyb.plugin.event.SpecResultEvent;

public class EasybProcessHandler extends ProcessHandler {
    public void startNotify() {
        addProcessListener(new ProcessAdapter() {
            public void startNotified(ProcessEvent event) {
                Runnable runnable = new Runnable() {
                    public void run() {
                        try {
                            for (int i = 0; i < 4; i++) {
                                EasybBuilder.getPresenter().eventFired(new SpecResultEvent(new SpecResult("scenario")));
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        testingComplete();
                    }
                };
                new Thread(runnable).start();
            }
        });
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
