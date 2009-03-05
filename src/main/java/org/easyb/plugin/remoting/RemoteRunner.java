package org.easyb.plugin.remoting;

import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Collections;

import org.easyb.BehaviorRunner;
import org.easyb.ConsoleReporter;
import org.easyb.report.ReportWriter;

public class RemoteRunner extends BehaviorRunner {
    public RemoteRunner(int port) throws IOException {
        super(Collections.<ReportWriter>emptyList(), new ConsoleReporter(), new ExecutionListenerProxy(port));
    }

    public static void main(String[] args) {
        System.out.println("Remote runner");
        int port = Integer.parseInt(args[0]);
        System.out.println("Port: " + port);

        try {
            BehaviorRunner runner = new RemoteRunner(port);
            runner.runBehavior(getBehaviors(stripFirst(args)));
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
