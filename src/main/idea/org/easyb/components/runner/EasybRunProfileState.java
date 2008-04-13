package org.easyb.components.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.configurations.ConfigurationPerRunnerSettings;
import com.intellij.execution.configurations.RunnableState;
import com.intellij.execution.configurations.RunnerSettings;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ExecutionConsole;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.Nullable;
import org.easyb.plugin.ui.swing.SwingEasybBuilder;

public class EasybRunProfileState implements RunnableState {
    private RunnerSettings runnerSettings;
    private ConfigurationPerRunnerSettings configurationSettings;
    private Module module;
    private String specificationPath;

    protected EasybRunProfileState(RunnerSettings runnerSettings, ConfigurationPerRunnerSettings configurationSettings,
            Module module, String specificationPath) {
        this.runnerSettings = runnerSettings;
        this.configurationSettings = configurationSettings;
        this.module = module;
        this.specificationPath = specificationPath;
    }

    @Nullable
    public ExecutionResult execute() throws ExecutionException {
        final SwingEasybBuilder builder = new SwingEasybBuilder();
        return new ExecutionResult() {
            public ExecutionConsole getExecutionConsole() {
                return new EasybConsoleView(builder.getView());
            }

            public AnAction[] getActions() {
                return new AnAction[0];
            }

            public ProcessHandler getProcessHandler() {
                return new EasybProcessHandler(builder.getPresenter(), specificationPath);
            }
        };
    }

    public RunnerSettings getRunnerSettings() {
        return runnerSettings;
    }

    public ConfigurationPerRunnerSettings getConfigurationSettings() {
        return configurationSettings;
    }

    public Module[] getModulesToCompile() {
        return new Module[]{module};
    }
}
