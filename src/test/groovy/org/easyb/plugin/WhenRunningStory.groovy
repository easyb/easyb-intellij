package org.easyb.plugin

import static junit.framework.Assert.assertEquals
import static org.disco.easyb.util.BehaviorStepType.*
import static org.easyb.plugin.Outcome.FAILURE
import static org.easyb.plugin.Outcome.SUCCESS
import org.easyb.plugin.ui.EasybPresenter
import org.easyb.plugin.ui.swing.EasybTreeNode
import static org.easyb.plugin.ui.swing.EasybTreeNode.nodeFor
import org.easyb.simulator.EasybSimulator
import org.easyb.simulator.SimulationNodeBuilder
import org.junit.Before
import org.junit.Test

public class WhenRunningStory {
    private StubView view;
    private EasybPresenter presenter;
    private EasybSimulator simulator;

    @Before
    public void setUp() {
        view = new StubView()
        presenter = new EasybPresenter(view)
        simulator = new EasybSimulator(listener: presenter)
    }

    @Test
    public void shouldAddNodesToTree() {
        EasybTreeNode scenarioNode = nodeFor(SCENARIO, 'Amount exceeds available funds', FAILURE);
        scenarioNode.add(nodeFor(GIVEN, 'An account balance of $100', SUCCESS));
        scenarioNode.add(nodeFor(WHEN, 'A transfer of $150 is requested', SUCCESS));
        scenarioNode.add(nodeFor(THEN, 'The request should be rejected', SUCCESS));
        scenarioNode.add(nodeFor(THEN, 'No funds should be transferred', FAILURE));

        EasybTreeNode storyNode = nodeFor(STORY, 'Transferring funds', FAILURE);
        storyNode.add(scenarioNode);

        simulator.replay(new SimulationNodeBuilder().story('Transferring funds') {
            scenario('Amount exceeds available funds')
                    {
                        given 'An account balance of $100'
                        when 'A transfer of $150 is requested'
                        then 'The request should be rejected'
                        then('No funds should be transferred') { fail() }
                    }
        })

        assertEquals(storyNode, view.getResultNode());
    }
}
