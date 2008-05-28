package org.easyb.plugin

import org.disco.easyb.BehaviorStep
import org.disco.easyb.listener.ExecutionListener
import org.disco.easyb.result.Result
import static org.disco.easyb.util.BehaviorStepType.GIVEN
import static org.disco.easyb.util.BehaviorStepType.STORY
import org.junit.Test
import static org.mockito.Mockito.*

class WhenReplayingSimulator {
    @Test
    public void shouldRecursivelyInvokeExecutionListenerMethods() {
        ExecutionListener mockListener = mock(ExecutionListener.class)

        ExecutionStepNode story = new ExecutionStepNode(step: new BehaviorStep(STORY, 'transferring funds'))
        ExecutionStepNode given = new ExecutionStepNode(step: new BehaviorStep(GIVEN, 'an account'))
        ExecutionStepNode result = new ExecutionStepNode(result: new Result(Result.SUCCEEDED))

        story.add(given)
        given.add(result)

        EasybSimulator simulator = new EasybSimulator(listener: mockListener)
        simulator.replay(story)

        verify(mockListener).startStep(story.step)
        verify(mockListener).startStep(given.step)
        verify(mockListener).gotResult(result.result)
        verify(mockListener, times(2)).stopStep()
    }
}