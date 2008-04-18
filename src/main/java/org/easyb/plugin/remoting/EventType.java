package org.easyb.plugin.remoting;

import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.domain.Behavior;
import org.disco.easyb.BehaviorStep;

public enum EventType {
    START_BEHAVIOR {
        public void fire(ExecutionListener receiver, Object data) {
            receiver.startBehavior((Behavior) data);
        }
    },
    START_STEP {
        public void fire(ExecutionListener receiver, Object data) {
            receiver.startStep((BehaviorStep) data);
        }
    },
    DESCRIBE_STEP {
        public void fire(ExecutionListener receiver, Object data) {
        }
    },
    GOT_RESULT {
        public void fire(ExecutionListener receiver, Object data) {
        }
    },
    STOP_STEP {
        public void fire(ExecutionListener receiver, Object data) {
        }
    },
    STOP_BEHAVIOR {
        public void fire(ExecutionListener receiver, Object data) {
        }
    };

    public abstract void fire(ExecutionListener receiver, Object data);
}
