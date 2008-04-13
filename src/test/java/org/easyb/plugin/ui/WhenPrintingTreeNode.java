package org.easyb.plugin.ui;

import org.junit.Test;
import org.easyb.plugin.ui.swing.EasybTreeNode;
import static junit.framework.Assert.assertEquals;

public class WhenPrintingTreeNode {
    @Test
    public void shouldIndicateHierarchyInToString() {
        EasybTreeNode foo = new EasybTreeNode("foo");
        EasybTreeNode bar = new EasybTreeNode("bar");
        EasybTreeNode baz = new EasybTreeNode("baz");

        foo.add(bar);
        bar.add(baz);

        assertEquals("foo[bar[baz[] ] ]", foo.toString());
    }
}
