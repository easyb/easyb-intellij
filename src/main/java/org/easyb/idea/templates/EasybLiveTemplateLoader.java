package org.easyb.idea.templates;

import java.io.IOException;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.codeInsight.template.impl.TemplateSettings;
import com.intellij.codeInsight.template.impl.TemplateGroup;
import com.intellij.codeInsight.template.Template;
import org.jetbrains.annotations.NotNull;
import org.jdom.input.SAXBuilder;
import org.jdom.JDOMException;
import org.jdom.Document;

public class EasybLiveTemplateLoader implements ApplicationComponent {
    private static final String TEMPLATE_RESOURCE_PATH = "/easyb-live-templates.xml";

    @NotNull
    public String getComponentName() {
        return "easyb.template.loader";
    }

    public void initComponent() {
        TemplateSettings settings = TemplateSettings.getInstance();
        try {
            TemplateGroup templates = settings.readTemplateFile(openTemplatesAsDocument(), "easyb", true, true);
            if (templates != null) {
                for (Template each : templates.getElements()) {
                    settings.addTemplate(each);
                }
            }
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        }
    }

    private Document openTemplatesAsDocument() {
        SAXBuilder builder = new SAXBuilder();
        try {
            return builder.build(getClass().getResourceAsStream(TEMPLATE_RESOURCE_PATH));
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disposeComponent() {
    }
}
