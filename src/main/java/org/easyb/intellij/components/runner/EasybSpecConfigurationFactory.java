package org.easyb.intellij.components.runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;

public class EasybSpecConfigurationFactory extends ConfigurationFactory {
    public EasybSpecConfigurationFactory(EasybSpecRunConfigurationType easybSpecRunConfigurationType) {
        super(easybSpecRunConfigurationType);
    }

    public RunConfiguration createTemplateConfiguration(Project project) {
        return new EasybSpecRunConfiguration(this, project, "Easyb Spec");
    }
}
