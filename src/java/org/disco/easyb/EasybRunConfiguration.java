package org.disco.easyb;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.*;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.runners.JavaProgramRunner;
import com.intellij.execution.runners.RunnerInfo;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.vfs.VirtualFile;

public class EasybRunConfiguration extends RunConfigurationBase {
    private String specificationPath;
    private Module classpathModule;

    protected EasybRunConfiguration(Project project, ConfigurationFactory factory, String name) {
        super(project, factory, name);
    }

    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new EasybRunConfigurationEditor();
    }

    public JDOMExternalizable createRunnerSettings(ConfigurationInfoProvider provider) {
        return null;
    }

    public SettingsEditor<JDOMExternalizable> getRunnerSettingsEditor(JavaProgramRunner runner) {
        return null;
    }

    public RunProfileState getState(DataContext context, RunnerInfo runnerInfo, RunnerSettings runnerSettings,
                                    ConfigurationPerRunnerSettings configurationSettings) throws ExecutionException {
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

    public void checkConfiguration() throws RuntimeConfigurationException {
    }

    public String getSpecificationPath() {
        return specificationPath;
    }

    public void setSpecificationPath(String specificationPath) {
        this.specificationPath = specificationPath;
    }

    /**
     * Return modules to compile before run. Null or empty list to make project
     */
    public Module[] getModules() {
        return ModuleManager.getInstance(getProject()).getModules();
    }

    public void setClasspathModule(Module classpathModule) {
        this.classpathModule = classpathModule;
    }
}
