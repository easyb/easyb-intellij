package org.easyb.idea.filetypes;

import com.intellij.openapi.fileTypes.FileNameMatcher;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.fileTypes.FileTypeManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class EasybFileTypeFactory extends FileTypeFactory {
  @Override
  public void createFileTypes(@NotNull FileTypeConsumer consumer) {
    consumer.consume( FileTypeManager.getInstance().getStdFileType("easyb"), new FileNameMatcher() {
      public boolean accept(@NonNls @NotNull String fileName) {
        String fName = fileName.toLowerCase();
        return fName.endsWith(".story") || fName.endsWith(".specification");
      }

      @NotNull
      public String getPresentableString() {
        return "easyb story";
      }
    });
  }
}
