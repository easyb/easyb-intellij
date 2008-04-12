package org.easyb.components.runner;

import org.easyb.plugin.EasybBuilder;
import org.easyb.plugin.SpecResult;
import org.easyb.plugin.event.SpecResultEvent;

public class EasybRunner implements Runnable {
    private EasybProcessHandler handler;

    public EasybRunner(EasybProcessHandler handler) {
        this.handler = handler;
    }

    public void run() {
        try {
            Thread.sleep(2000);
            EasybBuilder.getPresenter().eventFired(new SpecResultEvent(new SpecResult("pushing null onto stack")));
            Thread.sleep(2000);
            EasybBuilder.getPresenter().eventFired(new SpecResultEvent(new SpecResult("pushing item onto empty stack")));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handler.testingComplete();
    }
}
