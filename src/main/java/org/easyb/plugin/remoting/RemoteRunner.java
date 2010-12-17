package org.easyb.plugin.remoting;

import org.easyb.BehaviorRunner;
import org.easyb.Configuration;
import org.easyb.listener.ConsoleReporterListener;
import org.easyb.listener.ExecutionListener;
import org.easyb.listener.ListenerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class RemoteRunner extends BehaviorRunner {

  public RemoteRunner(final int port) throws IOException {
    super(new Configuration(),
      new ListenerBuilder() {
        public ExecutionListener get() {
          try {
            return new ExecutionListenerProxy(port);
          } catch (IOException iex) {
            throw new RuntimeException("Failed to connect to remote proxy", iex);
          }
        }
      }
      ,
      new ListenerBuilder() {
          public ExecutionListener get
            () {
            return new ConsoleReporterListener();
          }
        }
    );

  }

  public static void main(String[] args) {
    System.out.println("Remote runner");
    int port = Integer.parseInt(args[0]);
    System.out.println("Port: " + port);

    try {
      BehaviorRunner runner = new RemoteRunner(port);
      runner.runBehaviors(getBehaviors(stripFirst(args)));
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

  private static String[] stripFirst(String[] args) {
    List<String> argList = new ArrayList<String>(asList(args));
    argList.remove(0);
    return argList.toArray(new String[args.length - 1]);
  }
}
