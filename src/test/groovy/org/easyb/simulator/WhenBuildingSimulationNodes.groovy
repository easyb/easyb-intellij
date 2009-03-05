package org.easyb.simulator

import org.easyb.BehaviorStep
import static org.easyb.util.BehaviorStepType.*
import static org.junit.Assert.assertEquals
import org.junit.Test

class WhenBuildingSimulationNodes {
    @Test
    void shouldBuildHierarchically() {

        SimulationNodeBuilder builder = new SimulationNodeBuilder()
        def story = builder.story('sample story') {
            scenario('sample scenario') {
                given('something')
                when('something happens')
                then('something happened')
            }
        }

        SimulationNode expected = new SimulationNode(step: new BehaviorStep(STORY, 'sample story'))
        SimulationNode scenarioNode = new SimulationNode(step: new BehaviorStep(SCENARIO, 'sample scenario'))
        SimulationNode givenNode = new SimulationNode(step: new BehaviorStep(GIVEN, 'something'))
        SimulationNode whenNode = new SimulationNode(step: new BehaviorStep(WHEN, 'something happens'))
        SimulationNode thenNode = new SimulationNode(step: new BehaviorStep(THEN, 'something happened'))

        expected.add(scenarioNode)
        scenarioNode.add(givenNode)
        scenarioNode.add(whenNode)
        scenarioNode.add(thenNode)

        assertEquals expected, story
    }
}