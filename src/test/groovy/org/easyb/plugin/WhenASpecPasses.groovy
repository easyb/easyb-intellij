package org.easyb.plugin;

import static org.easymock.EasyMock.*;
import org.junit.Test;
import org.easyb.plugin.event.EasybEvent;
import org.easyb.plugin.event.SpecResultEvent;

public class WhenASpecPasses {
    def resultsBuilder = new SpecificationResultsBuilder()

    @Test
    public void shouldFireNewSpecResultEvent() {
        EasybEvent expectedEvent = new SpecResultEvent(new SpecResult("Push onto empty stack"))

        EasybRunner easybRunner = mockRunner('EmptyStack.story',
                resultsBuilder.specificationResults {passingSpec('Push onto empty stack')}
        )
        SpecEventListener listener = mockListener(expectedEvent)

        EasybPluginRunner pluginRunner = new EasybPluginRunner(easybRunner, listener)
        pluginRunner.executeSpecs(["EmptyStack.story"])

        verify(easybRunner)
        verify(listener)
    }

    private EasybRunner mockRunner(String spec, SpecificationResults results) {
        EasybRunner easybRunner = createMock(EasybRunner.class)

        easybRunner.executeSpec(spec)
        expectLastCall().andReturn(results)
        replay(easybRunner)

        return easybRunner
    }

    private SpecEventListener mockListener(EasybEvent event) {
        SpecEventListener listener = createMock(SpecEventListener.class)

        listener.eventFired(event)
        expectLastCall()
        replay(listener)

        return listener
    }
}
