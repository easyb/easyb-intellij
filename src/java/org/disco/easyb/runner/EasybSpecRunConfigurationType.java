package org.disco.easyb.runner;

import com.intellij.execution.LocatableConfigurationType;
import com.intellij.execution.Location;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Adds an easyb run configuration to IntelliJ
 */
public class EasybSpecRunConfigurationType implements LocatableConfigurationType {
    private EasybSpecConfigurationFactory factory;

    public EasybSpecRunConfigurationType() {
        factory = new EasybSpecConfigurationFactory(this);
    }

    public String getDisplayName() {
        return "Easyb Specification";
    }

    public String getConfigurationTypeDescription() {
        return "Easyb Specification";
    }

    public Icon getIcon() {
        return IconLoader.getIcon("/easyb.png");
    }

    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{factory};
    }

    @NonNls
    @NotNull
    public String getComponentName() {
        return "EasybSpecRunConfigurationType";
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    public RunnerAndConfigurationSettings createConfigurationByLocation(Location location) {
        final PsiElement element = location.getPsiElement();
        final PsiFile file = element.getContainingFile();
        if (file == null) return null;
        return createConfiguration(file);
    }

    private RunnerAndConfigurationSettings createConfiguration(final PsiFile easybSpecFile) {
        final Project project = easybSpecFile.getProject();
        RunnerAndConfigurationSettings settings = RunManager.getInstance(project).
                createRunConfiguration(easybSpecFile.getName(), factory);
        final EasybSpecRunConfiguration configuration = (EasybSpecRunConfiguration) settings.getConfiguration();
        final PsiDirectory dir = easybSpecFile.getContainingDirectory();
        assert dir != null;
        final VirtualFile vFile = easybSpecFile.getVirtualFile();
        assert vFile != null;
        configuration.setSpecificationPath(vFile.getPath());
        // TODO: This won't work on a multi-module project
        configuration.setModule(ModuleManager.getInstance(project).getModules()[0]);
        return settings;
    }

    public boolean isConfigurationByElement(RunConfiguration configuration, Project project, PsiElement element) {
        final VirtualFile vFile = element.getContainingFile().getVirtualFile();
        if (vFile == null) return false;
        return Comparing.equal(((EasybSpecRunConfiguration) configuration).getSpecificationPath(), vFile.getPath());
    }
}
