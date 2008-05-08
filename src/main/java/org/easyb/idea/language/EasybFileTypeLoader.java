package org.easyb.idea.language;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class EasybFileTypeLoader implements ApplicationComponent {
    public void initComponent() {
        FileTypeManager manager = FileTypeManager.getInstance();

        for (final FileType type : manager.getRegisteredFileTypes()) {
            if (type.getDefaultExtension().equals("groovy")) {
                addEasybAssociations(type);
            }
        }
    }

    private void addEasybAssociations(final FileType type) {
        ApplicationManager.getApplication().runWriteAction(
                new Runnable() {
                    public void run() {
                        FileTypeManager.getInstance().registerFileType(type, "story");
                        FileTypeManager.getInstance().registerFileType(type, "specification");
                    }
                }
        );
    }

    public void disposeComponent() {
    }

    @NonNls
    @NotNull
    public String getComponentName() {
        return "easyb.filetype.loader";
    }
}
