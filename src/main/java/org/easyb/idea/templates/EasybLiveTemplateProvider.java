package org.easyb.idea.templates;


import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider;

public class EasybLiveTemplateProvider implements DefaultLiveTemplatesProvider {
  public String[] getDefaultLiveTemplateFiles() {
    return new String[] {"/easyb-live-templates"};
  }

  public String[] getHiddenLiveTemplateFiles() {
    return new String[0];
  }
}
