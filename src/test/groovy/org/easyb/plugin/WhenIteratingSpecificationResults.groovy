package org.easyb.plugin

import static org.easyb.plugin.SpecResult.passingSpec
import org.junit.Test

public class WhenProcessingSpecificationResults {
    @Test
    public void specificationResultsShouldBeIterable() {
        def scenarios = ['Scenario A', 'Scenario B']

        SpecificationResults results = new SpecificationResults();
        scenarios.each {results.addResult(passingSpec(it))}

        def specNames = []
        for (SpecResult spec: results)
            specNames.add(spec.specName)

        assert scenarios == specNames
    }
}
