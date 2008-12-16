package org.easyb.idea.filetypes;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.FileTypeManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import static org.jetbrains.plugins.groovy.GroovyFileType.GROOVY_FILE_TYPE;

public class EasybFileTypeLoader implements ApplicationComponent {
    public void initComponent() {
        FileTypeManager.getInstance().registerFileType(GROOVY_FILE_TYPE, "story", "specification");
    }

    public void disposeComponent() {
    }

    @NonNls
    @NotNull
    public String getComponentName() {
        return "easyb.filetype.loader";
    }
}
