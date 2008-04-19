package org.easyb.idea.runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;

public class EasybConfigurationFactory extends ConfigurationFactory {
    public EasybConfigurationFactory(EasybRunConfigurationType easybRunConfigurationType) {
        super(easybRunConfigurationType);
    }

    public RunConfiguration createTemplateConfiguration(Project project) {
        return new EasybRunConfiguration(this, project, "Easyb Spec");
    }
}
