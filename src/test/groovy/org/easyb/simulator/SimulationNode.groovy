package org.easyb.simulator

import org.easyb.BehaviorStep
import org.easyb.result.Result

class SimulationNode {
    BehaviorStep step
    Result result

    def children = []

    def add(SimulationNode child) {
        children += child
    }

    boolean equals(Object other) {
        if (!(other instanceof SimulationNode))
            return false

        if (!(this.step.equals(other.step)) || !(this.result.equals(other.result)))
            return false

        return this.children.equals(other.children)
    }
}