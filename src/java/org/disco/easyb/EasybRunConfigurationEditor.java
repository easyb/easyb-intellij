package org.disco.easyb;

import javax.swing.*;

import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.NotNull;

public class EasybRunConfigurationEditor extends SettingsEditor<EasybRunConfiguration> {
    private EasybRunConfigurationForm form;

    protected void resetEditorFrom(EasybRunConfiguration configuration) {
    }

    protected void applyEditorTo(EasybRunConfiguration configuration) throws ConfigurationException {
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
