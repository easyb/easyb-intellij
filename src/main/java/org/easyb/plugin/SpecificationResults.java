package org.easyb.plugin;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class SpecificationResults implements Iterable<StepResult> {
    private List<StepResult> specResults = new ArrayList<StepResult>();

    public void addResult(StepResult specResult) {
        specResults.add(specResult);
    }

    public Iterator<StepResult> iterator() {
        return specResults.iterator();
    }
}
