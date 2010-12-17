package org.easyb.plugin.remoting;

import org.easyb.domain.Behavior;
import org.easyb.result.Result;

public interface RemotingExecutionListener {
  void startStep(RemotableBehaviorStep data);

  void startBehavior(RemotableBehavior data);

  void describeStep(String data);

  void gotResult(Result data);

  void stopStep();

  void stopBehavior(RemotableBehaviorStep step, RemotableBehavior data);

  void completeTesting();
}
