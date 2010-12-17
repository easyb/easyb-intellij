package org.easyb.simulator

import org.easyb.BehaviorStep
import org.easyb.listener.ExecutionListener
import org.easyb.result.Result
import static org.easyb.util.BehaviorStepType.GIVEN
import static org.easyb.util.BehaviorStepType.STORY
import org.junit.Test
import static org.mockito.Mockito.*
import org.easyb.plugin.remoting.RemotingExecutionListener
import org.easyb.plugin.remoting.RemotableBehaviorStep

class WhenReplayingSimulation {
    @Test
    void shouldRecursivelyInvokeExecutionListenerMethods() {
        RemotingExecutionListener mockListener = mock(RemotingExecutionListener.class)

        SimulationNode story = new SimulationNode(step: new RemotableBehaviorStep(STORY, 'transferring funds'))
        SimulationNode given = new SimulationNode(step: new RemotableBehaviorStep(GIVEN, 'an account'))
        SimulationNode result = new SimulationNode(result: new Result(Result.SUCCEEDED))

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