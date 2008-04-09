package org.easyb.intellij.components.runner;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

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

        moduleCombo.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, final Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                final Module module = (Module) value;
                if (module != null) {
                    setIcon(module.getModuleType().getNodeIcon(false));
                    setText(module.getName());
                }
                return this;
            }
        });

        return rootComponent;
    }

    protected void disposeEditor() {
    }
}
