package org.easyb.plugin;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class WhenIteratingSpecificationResults {
    @Test
    public void specificationResultsShouldBeIterable() {
        SpecificationResults results = new SpecificationResults();
        results.addResult(new SpecResult());
        results.addResult(new SpecResult());

        int count = 0;
        for (SpecResult spec : results) {
            assertNotNull(spec);
            count++;
        }

        assertEquals(2, count);
    }
}
