package org.easyb.plugin.ui;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class WhenComparingTreeNodes {
    @Test
    public void shouldCompareUserObjects() {
        assertThat(new EasybTreeNode("foo"), equalTo(new EasybTreeNode("foo")));
        assertThat(new EasybTreeNode("foo"), not(equalTo(new EasybTreeNode("bar"))));
    }

    @Test
    public void shouldCompareChildren() {
        EasybTreeNode parent1 = new EasybTreeNode("foo");
        parent1.add(new EasybTreeNode("bar"));

        EasybTreeNode parent2 = new EasybTreeNode("foo");
        parent2.add(new EasybTreeNode("baz"));

        assertThat(parent1, not(equalTo(parent2)));
    }
}
