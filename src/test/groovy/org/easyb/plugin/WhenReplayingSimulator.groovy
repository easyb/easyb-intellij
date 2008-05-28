package org.easyb.plugin

import org.disco.easyb.BehaviorStep
import org.disco.easyb.listener.ExecutionListener
import static org.disco.easyb.util.BehaviorStepType.GIVEN
import static org.disco.easyb.util.BehaviorStepType.STORY
import org.junit.Test
import static org.mockito.Mockito.*

class WhenReplayingSimulator {
    @Test
    public void shouldRecursivelyInvokeExecutionListenerMethods() {
        ExecutionListener mockListener = mock(ExecutionListener.class)

        BehaviorStep story = new BehaviorStep(STORY, 'transferring funds')
        BehaviorStep given = new BehaviorStep(GIVEN, 'an account')

        EasybSimulator simulator = new EasybSimulator(listener: mockListener)
        ExecutionStepNode node = new ExecutionStepNode(step: story)
        node.add(new ExecutionStepNode(step: given))
        simulator.replay(node)

        verify(mockListener).startStep(story)
        verify(mockListener).startStep(given)
        verify(mockListener, times(2)).stopStep()
    }
}