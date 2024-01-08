package com.example.testng;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.SkipException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class StepTest {

    private static final String GLOBAL_PARAMETER = "global value";

    @Ignore
    public void ignoredTest() {
    }

    @Test(enabled = false)
    public void disabledTest() {
    }

    @Test
    public void skippedTest() {
        throw new SkipException("mark test as skipped");
    }

    @Test
    public void annotatedStepTest() {
        annotatedStep("local value");
    }

    @Test
    public void lambdaStepTest() {
        final String localParameter = "parameter value";
        Allure.step(String.format("Parent lambda step with parameter [%s]", localParameter), (step) -> {
            step.parameter("parameter", localParameter);
            Allure.step(String.format("Nested lambda step with global parameter [%s]", GLOBAL_PARAMETER));
        });
    }

    @Step("Parent annotated step with parameter [{parameter}]")
    public void annotatedStep(final String parameter) {
        nestedAnnotatedStep();
    }

    @Step("Nested annotated step with global parameter [{this.GLOBAL_PARAMETER}]")
    public void nestedAnnotatedStep() {

    }

}
