package org.disco.easyb.runner;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class EasybRunConfigurationEditor extends SettingsEditor<EasybSpecRunConfiguration> {
    private JPanel rootComponent;
    private JComboBox moduleCombo;
    private JLabel moduleLabel;
    private JTextField specificationPathField;
    private JLabel specificationPathLabel;

    protected void resetEditorFrom(EasybSpecRunConfiguration configuration) {
        specificationPathField.setText(configuration.getSpecificationPath());
        moduleCombo.setModel(new DefaultComboBoxModel(configuration.getModules()));
    }

    protected void applyEditorTo(EasybSpecRunConfiguration configuration) throws ConfigurationException {
        configuration.setSpecificationPath(specificationPathField.getText());
        configuration.setClasspathModule((Module) moduleCombo.getSelectedItem());
    }

    @NotNull
    protected JComponent createEditor() {
        return rootComponent;
    }

    protected void disposeEditor() {
    }
}
