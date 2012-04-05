package org.easyb.idea.filetypes;

import com.intellij.openapi.fileTypes.*;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.nio.charset.Charset;

public class EasybFileTypeFactory extends FileTypeFactory {
  
  class EasybFileType implements FileType {

    private final String name;

    EasybFileType(String name) {

      this.name = name;
    }

    @NotNull
    public String getName() {
      return "easy " + name;
    }

    @NotNull
    public String getDescription() {
      return getName();
    }

    @NotNull
    public String getDefaultExtension() {
      return name;
    }

    public Icon getIcon() {
      return new ImageIcon(getClass().getResource("/easyb.png"));
    }

    public boolean isBinary() {
      return false;
    }

    public boolean isReadOnly() {
      return false;
    }

    public String getCharset(@NotNull VirtualFile file, byte[] content) {
      return Charset.defaultCharset().name();
    }
  }
  
  
  class EasybFileNameMatcher implements FileNameMatcher {
    private final String name;

    EasybFileNameMatcher(String name) {
      this.name = name;
    }

    public boolean accept(@NonNls @NotNull String fileName) {
      return fileName.endsWith("." + name);
    }

    @NotNull
    public String getPresentableString() {
      return "easyb " + name;
    }
  }
  
  @Override
  public void createFileTypes(@NotNull FileTypeConsumer consumer) {
    consumer.consume(new EasybFileType("story"), new EasybFileNameMatcher("story"));
    consumer.consume(new EasybFileType("specification"), new EasybFileNameMatcher("specification"));
  }
}
