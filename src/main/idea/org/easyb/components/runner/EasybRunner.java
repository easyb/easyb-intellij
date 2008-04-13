package org.easyb.components.runner;

import java.io.File;
import static java.util.Collections.singletonList;

import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import org.disco.easyb.BehaviorRunner;
import org.disco.easyb.domain.BehaviorFactory;
import org.easyb.plugin.ui.EasybPresenter;

public class EasybRunner extends ProcessAdapter implements Runnable {
    private EasybProcessHandler processHandler;
    private EasybPresenter presenter;
    private String specificationPath;

    public EasybRunner(EasybProcessHandler processHandler, EasybPresenter presenter, String specificationPath) {
        this.processHandler = processHandler;
        this.presenter = presenter;
        this.specificationPath = specificationPath;
    }

    public void run() {
        BehaviorRunner easybRunner = new BehaviorRunner(presenter);
        try {
            easybRunner.runBehavior(singletonList(BehaviorFactory.createBehavior(new File(specificationPath))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        processHandler.testingComplete();
    }

    public void startNotified(ProcessEvent event) {
        new Thread(this).start();
    }
}
