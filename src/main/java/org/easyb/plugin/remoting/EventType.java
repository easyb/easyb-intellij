package org.easyb.plugin.remoting;

import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.domain.Behavior;
import org.disco.easyb.BehaviorStep;
import org.disco.easyb.result.Result;

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
            receiver.describeStep((String) data);
        }
    },
    GOT_RESULT {
        public void fire(ExecutionListener receiver, Object data) {
            receiver.gotResult((Result) data);
        }
    },
    STOP_STEP {
        public void fire(ExecutionListener receiver, Object data) {
            receiver.stopStep();
        }
    },
    STOP_BEHAVIOR {
        public void fire(ExecutionListener receiver, Object data) {
            receiver.stopBehavior((Behavior) data);
        }
    },
    COMPLETE_TESTING {
        public void fire(ExecutionListener receiver, Object data) {
            receiver.completeTesting();
        }
    };

    public abstract void fire(ExecutionListener receiver, Object data);
}
