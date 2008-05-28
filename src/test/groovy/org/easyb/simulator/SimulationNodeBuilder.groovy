package org.easyb.simulator

import org.disco.easyb.util.BehaviorStepType
import org.disco.easyb.BehaviorStep
import org.disco.easyb.result.Result

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
        if ('fail'.equals(name.toString()))
            return new SimulationNode(result: new Result(Result.FAILED))

        return new SimulationNode(step: new BehaviorStep(BehaviorStepType.valueOf(name.toString().toUpperCase()), value.toString()))
    }
}