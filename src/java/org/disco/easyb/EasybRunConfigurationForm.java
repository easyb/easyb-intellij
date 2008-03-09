package org.disco.easyb;

import com.intellij.openapi.module.Module;

import javax.swing.*;

public class EasybRunConfigurationForm {
    private JPanel rootComponent;
    private JComboBox moduleCombo;
    private JLabel moduleLabel;
    private JTextField specificationPathField;
    private JLabel specificationPathLabel;

    public JPanel getRootComponent() {
        return rootComponent;
    }
    
    public void setSpecificationPath(String specificationPath) {
        specificationPathField.setText(specificationPath);
    }

    public String getSpecificationPath() {
        return specificationPathField.getText();
    }

    public void setAvailableClasspathModules(Module[] modules) {
        moduleCombo.setModel(new DefaultComboBoxModel(modules));
    }

    public Module getSelectedClasspathModule() {
        return (Module) moduleCombo.getSelectedItem();
    }
}
