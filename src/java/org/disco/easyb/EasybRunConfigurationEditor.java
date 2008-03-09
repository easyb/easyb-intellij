package org.disco.easyb;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class EasybRunConfigurationEditor extends SettingsEditor<EasybRunConfiguration> {
    private EasybRunConfigurationForm form;

    protected void resetEditorFrom(EasybRunConfiguration configuration) {
        form.setSpecificationPath(configuration.getSpecificationPath());
        form.setAvailableClasspathModules(configuration.getModules());
    }

    protected void applyEditorTo(EasybRunConfiguration configuration) throws ConfigurationException {
        configuration.setSpecificationPath(form.getSpecificationPath());
        configuration.setClasspathModule(form.getSelectedClasspathModule());
    }

    @NotNull
    protected JComponent createEditor() {
        if (form == null) {
            form = new EasybRunConfigurationForm();
        }
        return form.getRootComponent();
    }

    protected void disposeEditor() {
        form = null;
    }
}
