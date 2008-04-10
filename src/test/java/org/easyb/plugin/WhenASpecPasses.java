package org.easyb.plugin;

import static org.easymock.EasyMock.*;
import org.junit.Test;

public class WhenASpecPasses {
    @Test
    public void shouldFireSuccessEvent() {
        EasybRunner easybRunner = createMock(EasybRunner.class);
        SpecEventListener listener = createMock(SpecEventListener.class);

        easybRunner.executeSpec("EmptyStack.story");
        expectLastCall().andReturn("Spec Foo Passed");
        replay(easybRunner);

        listener.specPassed("Spec Foo Passed");
        expectLastCall();
        replay(listener);

        EasybPluginRunner pluginRunner = new EasybPluginRunner(easybRunner, listener);
        pluginRunner.executeSpecs(new String[]{"EmptyStack.story"});

        verify(easybRunner);
        verify(listener);
    }
}
