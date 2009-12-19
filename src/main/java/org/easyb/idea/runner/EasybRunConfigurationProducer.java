package org.easyb.idea.runner;

import com.intellij.execution.Location;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.impl.RunnerAndConfigurationSettingsImpl;
import com.intellij.execution.junit.RuntimeConfigurationProducer;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class EasybRunConfigurationProducer extends RuntimeConfigurationProducer implements Cloneable {
    private static final RunnerAndConfigurationSettingsImpl INCOMPATIBLE_TYPE = null;
    private PsiElement sourceElement;

    public EasybRunConfigurationProducer() {
        super(new EasybRunConfigurationType());
    }

    public PsiElement getSourceElement() {
        return sourceElement;
    }

    protected RunnerAndConfigurationSettingsImpl createConfigurationByElement(Location location, ConfigurationContext configurationContext) {
        if (!isSpec(location)) {
            sourceElement = null;
            return INCOMPATIBLE_TYPE;
        }

        sourceElement = location.getPsiElement();

        RunnerAndConfigurationSettingsImpl settings = cloneTemplateConfiguration(location.getProject(), configurationContext);
        EasybRunConfiguration configuration = (EasybRunConfiguration) settings.getConfiguration();

        // copied the logic for setting the module from com.intellij.execution.junit.JavaRuntimeConfigurationProducerBase
        final Module contextModule = configurationContext.getModule();
        final Module predefinedModule = ((ModuleBasedConfiguration)settings.getConfiguration()).getConfigurationModule().getModule();
        if (predefinedModule != null) {
            configuration.setModule(predefinedModule);
        }
        else if (configuration.getConfigurationModule().getModule() == null && contextModule != null) {
            configuration.setModule(contextModule);
        }

        configuration.setSpecificationPath(resolveSpecPath(location, configuration));
        configuration.setName(resolveSpecName(location));
        return settings;
    }

    private boolean isSpec(Location location) {
        PsiFile file = location.getPsiElement().getContainingFile();
        return file != null && (file.getName().endsWith(".story") || file.getName().endsWith(".specification"));
    }

    @SuppressWarnings({"ConstantConditions"})
    private String resolveSpecPath(Location location, EasybRunConfiguration configuration) {
        String modulePath = configuration.getModule().getModuleFilePath();
        return location.getOpenFileDescriptor().getFile().getPath().replace(modulePath, "");
    }

    @SuppressWarnings({"ConstantConditions"})
    private String resolveSpecName(Location location) {
        return location.getOpenFileDescriptor().getFile().getName();
    }

    public int compareTo(Object o) {
        return -1;
    }
}
