package org.disco.easyb.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.*;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.runners.RunnerInfo;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Arrays;
import java.util.Collection;

public class EasybSpecRunConfiguration extends ModuleBasedConfiguration {
    private EasybSpecConfigurationFactory factory;
    private String specificationPath;
    private Module classpathModule;

    public EasybSpecRunConfiguration(EasybSpecConfigurationFactory factory, Project project, String name) {
        super(name, new RunConfigurationModule(project, true), factory);
        this.factory = factory;
    }

    public Collection<Module> getValidModules() {
        return Arrays.asList(ModuleManager.getInstance(getProject()).getModules());
    }

    protected ModuleBasedConfiguration createInstance() {
        return new EasybSpecRunConfiguration(factory, getConfigurationModule().getProject(), getName());
    }

    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new EasybRunConfigurationEditor();
    }

    public RunProfileState getState(DataContext context, RunnerInfo runnerInfo, RunnerSettings runnerSettings, ConfigurationPerRunnerSettings configurationSettings) throws ExecutionException {
        JavaCommandLineState commandLineState = new JavaCommandLineState(runnerSettings, configurationSettings) {
            protected JavaParameters createJavaParameters() throws ExecutionException {
                ModuleRootManager rootManager = ModuleRootManager.getInstance(classpathModule);

                JavaParameters javaParameters = new JavaParameters();
                javaParameters.setJdk(rootManager.getJdk());
                OrderEntry[] entries = ModuleRootManager.getInstance(classpathModule).getOrderEntries();
                for (OrderEntry each : entries) {
                    for (VirtualFile file : each.getFiles(OrderRootType.CLASSES_AND_OUTPUT)) {
                        javaParameters.getClassPath().add(file);
                    }
                }
                javaParameters.setMainClass("org.disco.easyb.SpecificationRunner");
                javaParameters.getProgramParametersList().add(specificationPath);
                return javaParameters;
            }
        };

        commandLineState.setConsoleBuilder(TextConsoleBuilderFactory.getInstance().createBuilder(getProject()));

        return commandLineState;
    }

    public String getSpecificationPath() {
        return specificationPath;
    }

    public void setSpecificationPath(String specificationPath) {
        this.specificationPath = specificationPath;
    }

    public Module getClasspathModule() {
        return classpathModule;
    }

    public void setClasspathModule(Module classpathModule) {
        this.classpathModule = classpathModule;
    }
}
