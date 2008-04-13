package org.easyb.plugin.ui;

import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.easyb.plugin.ui.swing.EasybTreeNode;
import org.easyb.plugin.ui.swing.EasybTreeNodeStack;

public class WhenPerformingStackOperations {
    private EasybTreeNodeStack stack;

    @Before
    public void givenAStack() {
        stack = new EasybTreeNodeStack();
    }

    @Test
    public void shouldPushNodeOntoStack() {
        assertEquals(0, stack.size());
        stack.push(new EasybTreeNode(null));
        assertEquals(1, stack.size());
    }

    @Test
    public void shouldPopNodeFromStack() {
        EasybTreeNode node = new EasybTreeNode(null);
        stack.push(node);
        assertEquals(node, stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    public void shouldPeekAtLastNodeOnStack() {
        EasybTreeNode node = new EasybTreeNode(null);
        stack.push(node);
        assertEquals(node, stack.peek());
        assertEquals(1, stack.size());
    }
}
