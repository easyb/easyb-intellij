package org.easyb.plugin.ui;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SpecificationRunningParser {
    private Pattern specPattern = Pattern.compile("Running (.*?) (specification|story) \\(.*\\)\n*$");

    public String parseSpecNameFrom(String output) {
        Matcher matcher = specPattern.matcher(output);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            throw new IllegalStateException("Cannot parse specification name from output " + output);
        }
    }

    public boolean isSpecificationRunningMessage(String output) {
        Matcher matcher = specPattern.matcher(output);
        return matcher.matches();
    }
}
