package org.easyb.plugin;

public class SpecResult {
    private String specName;

    public SpecResult(String specName) {
        this.specName = specName;
    }

    public static SpecResult passingSpec(String specName) {
        return new SpecResult(specName);
    }

    public String getSpecName() {
        return specName;
    }
}
