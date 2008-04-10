package org.easyb.plugin;

import static org.easymock.EasyMock.*;
import org.junit.Test;

public class WhenASuccessEventIsReceived {
    @Test
    public void shouldAddResultToView() {
        EasybView view = createMock(EasybView.class);
        view.addSpecResult();
        expectLastCall();
        replay(view);

        SpecEventListener controller = new EasybController(view);
        controller.specPassed();

        verify(view);
    }
}
