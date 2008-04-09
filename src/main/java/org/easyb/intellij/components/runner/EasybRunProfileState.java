package org.easyb.intellij.components.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.ConfigurationPerRunnerSettings;
import com.intellij.execution.configurations.JavaCommandLineState;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.RunnerSettings;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.vfs.VirtualFile;

public class EasybRunProfileState extends JavaCommandLineState {
    private Module module;
    private String specificationPath;

    protected EasybRunProfileState(RunnerSettings runnerSettings, ConfigurationPerRunnerSettings configurationSettings,
            Module module, String specificationPath) {
        super(runnerSettings, configurationSettings);
        this.module = module;
        this.specificationPath = specificationPath;
    }

    protected JavaParameters createJavaParameters() throws ExecutionException {
        ModuleRootManager rootManager = ModuleRootManager.getInstance(module);

        JavaParameters javaParameters = new JavaParameters();
        javaParameters.setJdk(rootManager.getJdk());
        OrderEntry[] entries = ModuleRootManager.getInstance(module).getOrderEntries();
        for (OrderEntry each : entries) {
            for (VirtualFile file : each.getFiles(OrderRootType.CLASSES_AND_OUTPUT)) {
                javaParameters.getClassPath().add(file);
            }
        }
        javaParameters.setMainClass("org.disco.easyb.BehaviorRunner");
        javaParameters.getProgramParametersList().add(specificationPath);
        return javaParameters;
    }
}
