package org.easyb.plugin

import org.disco.easyb.BehaviorStep
import org.disco.easyb.result.Result

class ExecutionStepNode {
    BehaviorStep step
    Result result

    def children = []

    def add(ExecutionStepNode child) {
        children += child
    }
}