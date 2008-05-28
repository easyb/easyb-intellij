package org.easyb.plugin

import org.disco.easyb.BehaviorStep

class ExecutionStepNode {
    BehaviorStep step
    def children = []

    def add(ExecutionStepNode child) {
        children += child
    }
}