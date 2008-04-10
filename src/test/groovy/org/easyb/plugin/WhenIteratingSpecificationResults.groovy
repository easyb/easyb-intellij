package org.easyb.plugin

import org.junit.Test

public class WhenProcessingSpecificationResults {
    @Test
    public void specificationResultsShouldBeIterable() {
        def scenarios = ['Scenario A', 'Scenario B']

        def builder = new SpecificationResultsBuilder()
        def specResults = builder.specificationResults
        {
            scenarios.each {passingSpec(it)}
        }

        def specNames = []
        for (SpecResult spec: specResults)
            specNames.add(spec.specName)

        assert scenarios == specNames
    }
}
