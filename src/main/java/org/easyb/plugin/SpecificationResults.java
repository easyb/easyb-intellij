package org.easyb.plugin;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class SpecificationResults implements Iterable<SpecResult> {
    private List<SpecResult> specResults = new ArrayList<SpecResult>();

    public void addResult(SpecResult specResult) {
        specResults.add(specResult);
    }

    public Iterator<SpecResult> iterator() {
        return specResults.iterator();
    }
}
