package org.easyb.plugin;

import static org.easymock.EasyMock.*;
import org.junit.Test;
import org.easyb.plugin.event.SpecResultEvent;

public class WhenASuccessEventIsReceived {
    @Test
    public void shouldAddResultToView() {
        EasybView view = createMock(EasybView.class);
        view.addSpecResult("Spec Foo Passed");
        expectLastCall();
        replay(view);

        SpecEventListener controller = new EasybController(view);
        controller.eventFired(new SpecResultEvent("Spec Foo Passed"));

        verify(view);
    }
}
