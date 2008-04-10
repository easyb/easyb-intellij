package org.easyb.plugin;

import static org.easymock.EasyMock.*;
import org.junit.Test;

public class WhenASpecPasses {
    @Test
    public void shouldFireSuccessEvent() {
        EasybRunner easybRunner = runnerForStory("EmptyStack.story");
        SpecEventListener listener = listenerForEvent("EmptyStack Passed");

        EasybPluginRunner pluginRunner = new EasybPluginRunner(easybRunner, listener);
        pluginRunner.executeSpecs(new String[]{"EmptyStack.story"});

        verify(easybRunner);
        verify(listener);
    }

    private EasybRunner runnerForStory(String story) {
        EasybRunner easybRunner = createMock(EasybRunner.class);

        easybRunner.executeSpec(story);
        expectLastCall().andReturn(story.replace(".story", " Passed"));
        replay(easybRunner);

        return easybRunner;
    }

    private SpecEventListener listenerForEvent(String event) {
        SpecEventListener listener = createMock(SpecEventListener.class);

        listener.specPassed(event);
        expectLastCall();
        replay(listener);

        return listener;
    }
}
