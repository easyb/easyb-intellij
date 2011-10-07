package org.easyb.idea.runner;

import javax.swing.*;
import java.awt.*;

public class EasybRunConfigurationDialog extends JPanel {
    JTextField specificationPathField;
    JTextField jvmParametersField;
    JComboBox moduleCombo;
    DefaultComboBoxModel moduleComboModel;

    public EasybRunConfigurationDialog() {
        setLayout(new GridLayout(0, 1));

        add(new JLabel("Specification path:"));

        specificationPathField = new JTextField();
        add(specificationPathField);

        add(new JLabel("VM Parameters:"));
        jvmParametersField = new JTextField();
        add(jvmParametersField);

        add(new JLabel("Choose classpath and jdk from module:"));

        moduleComboModel = new DefaultComboBoxModel();
        moduleCombo = new JComboBox(moduleComboModel);
        add(moduleCombo);
    }
}
