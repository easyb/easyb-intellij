package org.easyb.simulator

import org.disco.easyb.util.BehaviorStepType
import org.disco.easyb.BehaviorStep

class SimulationNodeBuilder extends BuilderSupport {
    protected void setParent(Object parent, Object child) {
        parent.add(child)
    }

    protected Object createNode(Object name) {
        return createNode(name, [:], null)
    }

    protected Object createNode(Object name, Object value) {
        return createNode(name, [:], value)
    }

    protected Object createNode(Object name, Map attributes) {
        return createNode(name, attributes, null)
    }

    protected Object createNode(Object name, Map attributes, Object value) {
        return new SimulationNode(step: new BehaviorStep(BehaviorStepType.STORY, value.toString()))
    }
}