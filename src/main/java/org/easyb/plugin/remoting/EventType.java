package org.easyb.plugin.remoting;

import org.easyb.listener.ExecutionListener;
import org.easyb.domain.Behavior;
import org.easyb.BehaviorStep;
import org.easyb.result.Result;

public enum EventType {
    START_BEHAVIOR {
        public void fire(RemotingExecutionListener receiver, Object data) {
            receiver.startBehavior((RemotableBehavior)data);
        }
    },
    START_STEP {
        public void fire(RemotingExecutionListener receiver, Object data) {
            receiver.startStep((RemotableBehaviorStep) data);
        }
    },
    DESCRIBE_STEP {
        public void fire(RemotingExecutionListener receiver, Object data) {
            receiver.describeStep((String) data);
        }
    },
    GOT_RESULT {
        public void fire(RemotingExecutionListener receiver, Object data) {
            receiver.gotResult((Result) data);
        }
    },
    STOP_STEP {
        public void fire(RemotingExecutionListener receiver, Object data) {
            receiver.stopStep();
        }
    },
    STOP_BEHAVIOR {
        public void fire(RemotingExecutionListener receiver, Object data) {
            receiver.stopBehavior((RemotableBehaviorStep)null, (RemotableBehavior) data);
        }
    },
    COMPLETE_TESTING {
        public void fire(RemotingExecutionListener receiver, Object data) {
            receiver.completeTesting();
        }
    };

    public abstract void fire(RemotingExecutionListener receiver, Object data);
}
