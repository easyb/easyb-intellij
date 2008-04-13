package org.easyb.plugin.ui.swing

import org.junit.Test
import org.easyb.plugin.ui.SwingEasybView

class WhenConstructingView {
    @Test
    public void shouldBuildViewComponentsWithoutError() {
        new SwingEasybView();
    }
}