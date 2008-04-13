package org.easyb.plugin.ui.swing

import groovy.ui.Console
import javax.swing.JFrame
import static org.disco.easyb.util.BehaviorStepType.*
import org.disco.easyb.util.BehaviorStepType
import org.easyb.plugin.RunResult
import org.easyb.plugin.StepResult
import org.easyb.plugin.ui.swing.EasybTreeNode
import org.easyb.plugin.ui.swing.SwingEasybView

class SwingEasybViewHarness {
    public static void main(String[] args) {
        SwingEasybView view = new SwingEasybView()
        JFrame frame = new JFrame();
        frame.add(view);
        frame.show();
        frame.setSize(300, 200);

        view.addBehaviorResult(buildStory())

        Console console = new Console()
        console.setVariable('frame', frame)
        console.setVariable('view', view)
        console.run()
    }

    private static EasybTreeNode buildStory() {
        EasybTreeNode scenarioNode = nodeFor(SCENARIO, 'amount exceeds available funds')
        scenarioNode.add(nodeFor(GIVEN, 'an account balance of $100'))
        scenarioNode.add(nodeFor(WHEN, 'a transfer of $150 is requested'))
        scenarioNode.add(nodeFor(THEN, 'the request should be rejected'))

        EasybTreeNode storyNode = nodeFor(STORY, "transferring funds")
        storyNode.add(scenarioNode)

        return storyNode
    }

    private static EasybTreeNode nodeFor(BehaviorStepType type, String phrase) {
        return new EasybTreeNode(new StepResult(phrase, type, RunResult.SUCCESS));
    }
}