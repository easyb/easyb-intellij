package org.easyb.plugin

import org.disco.easyb.listener.ExecutionListener

class EasybSimulator {
    ExecutionListener listener

    void replay(ExecutionStepNode node) {
        listener.startStep(node.getStep())
        node.children.each {
            replay(it)
        }
        listener.stopStep()
    }
}