package org.easyb.plugin.ui.swing

import groovy.ui.Console
import javax.swing.JFrame
import javax.swing.tree.DefaultMutableTreeNode
import static org.disco.easyb.util.BehaviorStepType.*
import org.disco.easyb.util.BehaviorStepType
import org.easyb.plugin.Outcome
import org.easyb.plugin.StepResult
import org.easyb.plugin.ui.EasybView
import org.easyb.plugin.ui.ViewEventListener
import org.easyb.plugin.ui.swing.SwingEasybView
import org.easyb.plugin.ui.swing.SwingResultNode

class SwingEasybViewHarness {
    SwingEasybView view
    DefaultMutableTreeNode storyNode;

    public SwingEasybViewHarness() {
        storyNode = nodeFor(STORY, "transferring funds", Outcome.RUNNING)
    }

    public void run() {
        view = new SwingEasybView()
        view.registerEventListener(new FakeViewEventListener(view))
        
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
        SwingResultNode scenarioNode = nodeFor(SCENARIO, 'amount exceeds available funds', Outcome.RUNNING)
        storyNode.add(scenarioNode)
        view.refresh()

        Thread.sleep(1000)
        scenarioNode.add(nodeFor(GIVEN, 'an account balance of $100', Outcome.SUCCESS))
        view.writeConsole 'an account balance of $100\n'
        view.refresh()
        Thread.sleep(1000)
        scenarioNode.add(nodeFor(WHEN, 'a transfer of $150 is requested', Outcome.SUCCESS))
        view.writeConsole 'a transfer of $150 is requested\n'
        view.refresh()
        Thread.sleep(1000)
        scenarioNode.add(nodeFor(THEN, 'the request should be rejected', Outcome.FAILURE))
        view.writeConsole 'the request should be rejected\n'
        view.refresh()
        Thread.sleep(1000)
        scenarioNode.add(nodeFor(THEN, 'and not funds should be transfered', Outcome.PENDING))
        view.writeConsole 'and not funds should be transfered\n'
        view.refresh()
    }

    private static SwingResultNode nodeFor(BehaviorStepType type, String phrase, Outcome result) {
        return new SwingResultNode(new StepResult(phrase, type, result));
    }
}

class FakeViewEventListener implements ViewEventListener {
    private EasybView view;

    public FakeViewEventListener(EasybView view) {
        this.view = view
    }

    public void resultSelected(StepResult result) {
        view.writeOutput "result output"
    }
}