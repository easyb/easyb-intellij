package org.easyb.simulator

import org.easyb.listener.ExecutionListener

class EasybSimulator {
    ExecutionListener listener

    void replay(SimulationNode node) {
        if (hasResult(node))
            simulateResult(node)
        else
            simulateStep(node)
    }

    boolean hasResult(def node) {
        return node.result != null
    }

    def simulateResult(def node) {
        listener.gotResult(node.result)
    }

    def simulateStep(def node) {
        listener.startStep(node.step)
        node.children.each {
            replay(it)
        }
        listener.stopStep()
    }
}