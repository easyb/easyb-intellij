package org.easyb.components.runner;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.configurations.ConfigurationPerRunnerSettings;
import com.intellij.execution.configurations.JavaCommandLineState;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.RunnerSettings;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathUtil;
import org.easyb.plugin.ui.swing.SwingEasybBuilder;
import org.easyb.plugin.remoting.RemoteExecutionListener;
import org.disco.easyb.BehaviorRunner;

public class EasybRunProfileState extends JavaCommandLineState {
    private Module module;
    private String specificationPath;
    private SwingEasybBuilder builder;

    protected EasybRunProfileState(RunnerSettings runnerSettings, ConfigurationPerRunnerSettings configurationSettings,
            Module module, String specificationPath) {
        super(runnerSettings, configurationSettings);
        this.module = module;
        this.specificationPath = specificationPath;
        this.builder = new SwingEasybBuilder();
    }

    public ExecutionResult execute() throws ExecutionException {
        ProcessHandler processHandler = startProcess();
        EasybConsoleView console = new EasybConsoleView(builder.getView());
        return new DefaultExecutionResult(console, processHandler, createActions(console, processHandler));
    }

    protected JavaParameters createJavaParameters() throws ExecutionException {
        RemoteExecutionListener listener = new RemoteExecutionListener();
        listener.setReceiver(builder.getPresenter());
        listener.start();

        JavaParameters javaParameters = new JavaParameters();
        javaParameters.setJdk(ModuleRootManager.getInstance(module).getJdk());
        for (VirtualFile file : getProjectClasspath()) {
            javaParameters.getClassPath().add(file);
        }
        javaParameters.getClassPath().add(PathUtil.getJarPathForClass(getClass()));
        javaParameters.getClassPath().add(PathUtil.getJarPathForClass(BehaviorRunner.class));
        javaParameters.setMainClass("org.easyb.plugin.remoting.RemoteRunner");
        javaParameters.getProgramParametersList().add(Integer.toString(listener.getPort()));
        javaParameters.getProgramParametersList().add(specificationPath);
        return javaParameters;
    }

    @SuppressWarnings("deprecation")
    private VirtualFile[] getProjectClasspath() {
        return ProjectRootManager.getInstance(module.getProject()).getFullClassPath();
    }
}
