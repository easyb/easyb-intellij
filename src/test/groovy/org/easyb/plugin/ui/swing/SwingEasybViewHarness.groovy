package org.easyb.plugin.ui.swing

import groovy.ui.Console
import javax.swing.JFrame
import static org.disco.easyb.util.BehaviorStepType.*
import org.disco.easyb.util.BehaviorStepType
import org.easyb.plugin.Outcome
import org.easyb.plugin.StepResult
import org.easyb.plugin.ui.swing.EasybTreeNode
import org.easyb.plugin.ui.swing.SwingEasybView
import javax.swing.tree.DefaultMutableTreeNode

class SwingEasybViewHarness {
    SwingEasybView view
    DefaultMutableTreeNode storyNode;

    public SwingEasybViewHarness() {
        storyNode = nodeFor(STORY, "transferring funds", Outcome.RUNNING)
    }

    public void run() {
        view = new SwingEasybView()
        JFrame frame = new JFrame();
        frame.add(view);
        frame.show();
        frame.setSize(600, 400);

        view.addBehaviorResult(storyNode)
        playStory()

        Console console = new Console()
        console.setVariable('frame', frame)
        console.setVariable('view', view)
        console.run()
    }

    public static void main(String[] args) {
        new SwingEasybViewHarness().run();
    }

    private void playStory() {
        EasybTreeNode scenarioNode = nodeFor(SCENARIO, 'amount exceeds available funds', Outcome.RUNNING)
        storyNode.add(scenarioNode)
        view.refresh()

        Thread.sleep(1000)
        scenarioNode.add(nodeFor(GIVEN, 'an account balance of $100', Outcome.SUCCESS))
        view.writeOutput 'an account balance of $100\n'
        view.refresh()
        Thread.sleep(1000)
        scenarioNode.add(nodeFor(WHEN, 'a transfer of $150 is requested', Outcome.SUCCESS))
        view.writeOutput 'a transfer of $150 is requested\n'
        view.refresh()
        Thread.sleep(1000)
        scenarioNode.add(nodeFor(THEN, 'the request should be rejected', Outcome.FAILURE))
        view.writeOutput 'the request should be rejected\n'
        view.refresh()
        Thread.sleep(1000)
        scenarioNode.add(nodeFor(THEN, 'and not funds should be transfered', Outcome.PENDING))
        view.writeOutput 'and not funds should be transfered\n'
        view.refresh()
    }

    private static EasybTreeNode nodeFor(BehaviorStepType type, String phrase, Outcome result) {
        return new EasybTreeNode(new StepResult(phrase, type, result));
    }
}