package org.easyb.idea.runner;

import java.awt.*;
import javax.swing.*;

public class EasybRunConfigurationDialog extends JPanel {
    JTextField specificationPathField;
    JComboBox moduleCombo;
    DefaultComboBoxModel moduleComboModel;

    public EasybRunConfigurationDialog() {
        setLayout(new GridLayout(0, 1));

        add(new JLabel("Specification path:"));

        specificationPathField = new JTextField();
        add(specificationPathField);

        add(new JLabel("Choose classpath and jdk from module:"));

        moduleComboModel = new DefaultComboBoxModel();
        moduleCombo = new JComboBox(moduleComboModel);
        add(moduleCombo);
    }
}
