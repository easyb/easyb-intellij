package org.easyb.plugin.ui;

import org.easyb.BehaviorStep;
import org.easyb.exception.VerificationException;
import org.easyb.result.Result;
import static org.easyb.util.BehaviorStepType.STORY;
import org.easyb.plugin.StubView;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class WhenPresenterGetsResultWithException {
    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    @Test
    public void shouldAskViewToDisplayDetails() {
        StubView view = new StubView();
        StubNodeBuilder builder = new StubNodeBuilder();

        EasybPresenter<StubResultNode> presenter = new EasybPresenter<StubResultNode>(view, builder);
        VerificationException failure = new VerificationException("Result did not match exectation");
        presenter.startStep(new BehaviorStep(STORY, "test story"));
        presenter.gotResult(new Result(failure));

        assertEquals(failure, view.getResultNode().getResult().getCause());
    }
}
