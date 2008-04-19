package org.easyb.idea.runner;

import java.awt.*;
import javax.swing.*;

import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.NotNull;

public class EasybRunConfigurationEditor extends SettingsEditor<EasybRunConfiguration> {
    private EasybRunConfigurationDialog dialog = new EasybRunConfigurationDialog();

    protected void resetEditorFrom(EasybRunConfiguration configuration) {
        dialog.specificationPathField.setText(configuration.getSpecificationPath());

        dialog.moduleComboModel.removeAllElements();
        for (Module module : configuration.getValidModules()) {
            dialog.moduleComboModel.addElement(module);
        }
        dialog.moduleComboModel.setSelectedItem(configuration.getModule());
    }

    protected void applyEditorTo(EasybRunConfiguration configuration) throws ConfigurationException {
        configuration.setSpecificationPath(dialog.specificationPathField.getText());
        configuration.setModule((Module) dialog.moduleCombo.getSelectedItem());
    }

    @NotNull
    protected JComponent createEditor() {
        dialog.moduleComboModel = new DefaultComboBoxModel();
        dialog.moduleCombo.setModel(dialog.moduleComboModel);

        dialog.moduleCombo.setRenderer(new DefaultListCellRenderer() {
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

        return dialog;
    }

    protected void disposeEditor() {
    }
}