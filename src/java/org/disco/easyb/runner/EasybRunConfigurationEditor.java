package org.disco.easyb.runner;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class EasybRunConfigurationEditor extends SettingsEditor<EasybSpecRunConfiguration> {
    private JPanel rootComponent;
    private JComboBox moduleCombo;
    private DefaultComboBoxModel moduleComboModel;
    private JLabel moduleLabel;
    private JTextField specificationPathField;
    private JLabel specificationPathLabel;

    protected void resetEditorFrom(EasybSpecRunConfiguration configuration) {
        specificationPathField.setText(configuration.getSpecificationPath());

        moduleComboModel.removeAllElements();
        for (Module module : configuration.getValidModules()) {
            moduleComboModel.addElement(module);
        }
        moduleComboModel.setSelectedItem(configuration.getModule());
    }

    protected void applyEditorTo(EasybSpecRunConfiguration configuration) throws ConfigurationException {
        configuration.setSpecificationPath(specificationPathField.getText());
        configuration.setModule((Module) moduleCombo.getSelectedItem());
    }

    @NotNull
    protected JComponent createEditor() {
        moduleComboModel = new DefaultComboBoxModel();
        moduleCombo.setModel(moduleComboModel);

        return rootComponent;
    }

    protected void disposeEditor() {
    }
}
