package org.easyb.plugin;

import static org.easymock.EasyMock.*;
import org.junit.Test;

public class WhenATestPasses {
    @Test
    public void shouldFireSuccessEvent() {
        EasybRunner easybRunner = createMock(EasybRunner.class);
        SpecEventListener listener = createMock(SpecEventListener.class);

        easybRunner.executeSpec("EmptyStack.story");
        expectLastCall().andReturn(true);
        replay(easybRunner);

        listener.specPassed();
        expectLastCall();
        replay(listener);

        EasybPluginRunner pluginRunner = new EasybPluginRunner(easybRunner, listener);
        pluginRunner.executeSpecs(new String[]{"EmptyStack.story"});

        verify(easybRunner);
        verify(listener);
    }
}
