package org.easyb.plugin;

import static org.easymock.EasyMock.*;
import org.junit.Test;
import org.easyb.plugin.event.EasybEvent;
import org.easyb.plugin.event.SpecResultEvent;

public class WhenASpecPasses {
    def resultsBuilder = new SpecificationResultsBuilder()

    @Test
    public void shouldFireNewSpecResultEvent() {
        EasybRunner easybRunner = runnerFor('EmptyStack.story', withResults {passingSpec('Push onto empty stack')})
        SpecEventListener listener = listenerFor(new SpecResultEvent(new SpecResult('Push onto empty stack')))

        new EasybPluginRunner(easybRunner, listener).executeSpecs(["EmptyStack.story"])

        verify(easybRunner)
        verify(listener)
    }

    private EasybRunner runnerFor(String spec, SpecificationResults results) {
        EasybRunner easybRunner = createMock(EasybRunner.class)

        easybRunner.executeSpec(spec)
        expectLastCall().andReturn(results)
        replay(easybRunner)

        return easybRunner
    }

    private SpecificationResults withResults(Closure results) {
        resultsBuilder.specificationResults results
    }

    private SpecEventListener listenerFor(EasybEvent event) {
        SpecEventListener listener = createMock(SpecEventListener.class)

        listener.eventFired(event)
        expectLastCall()
        replay(listener)

        return listener
    }
}
