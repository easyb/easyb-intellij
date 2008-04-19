package org.easyb.idea.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class EasybFileType extends LanguageFileType {
    public EasybFileType() {
        super(new EasybLanguage());
    }

    @NotNull
    public String getName() {
        return "EasybStory";
    }

    @NotNull
    public String getDescription() {
        return "easyb story";
    }

    @NotNull
    public String getDefaultExtension() {
        return "story";
    }

    public Icon getIcon() {
        return IconLoader.getIcon("/easyb.png");
    }
}
