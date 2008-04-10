package org.easyb.plugin;

public class SpecResult {
    private String specName;

    public SpecResult(String specName) {
        this.specName = specName;
    }

    public String getSpecName() {
        return specName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpecResult that = (SpecResult) o;

        return !(specName != null ? !specName.equals(that.specName) : that.specName != null);
    }

    public int hashCode() {
        return (specName != null ? specName.hashCode() : 0);
    }
}
