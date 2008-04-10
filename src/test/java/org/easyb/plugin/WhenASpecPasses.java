package org.easyb.plugin;

import static org.easymock.EasyMock.*;
import org.junit.Test;
import org.easyb.plugin.event.EasybEvent;
import org.easyb.plugin.event.SpecResultEvent;

public class WhenASpecPasses {
    @Test
    public void shouldFireNewSpecResultEvent() {
        EasybEvent expectedEvent = new SpecResultEvent("EmptyStack story passed");

        EasybRunner easybRunner = runnerForStory("EmptyStack.story");
        SpecEventListener listener = listenerForEvent(expectedEvent);

        EasybPluginRunner pluginRunner = new EasybPluginRunner(easybRunner, listener);
        pluginRunner.executeSpecs(new String[]{"EmptyStack.story"});

        verify(easybRunner);
        verify(listener);
    }

    private EasybRunner runnerForStory(String story) {
        EasybRunner easybRunner = createMock(EasybRunner.class);

        easybRunner.executeSpec(story);
        expectLastCall();
        replay(easybRunner);

        return easybRunner;
    }

    private SpecEventListener listenerForEvent(EasybEvent event) {
        SpecEventListener listener = createMock(SpecEventListener.class);

        listener.eventFired(event);
        expectLastCall();
        replay(listener);

        return listener;
    }
}
