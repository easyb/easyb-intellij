package org.disco.easyb;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.openapi.project.Project;

public class EasybConfigurationFactory extends ConfigurationFactory {
    protected EasybConfigurationFactory(ConfigurationType type) {
        super(type);
    }

    public RunConfiguration createTemplateConfiguration(Project project) {
        return new EasybRunConfiguration(project, this, "");
    }

    public RunConfiguration createConfiguration(String name, RunConfiguration template) {
        return super.createConfiguration(name, template);
    }
}
