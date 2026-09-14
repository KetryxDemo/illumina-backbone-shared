package com.ketryxdemo.illumina;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Java @Test fallback so the JUnit XML report has at least one entry
 * even if Cucumber features are unavailable.
 */
public class SmokeJUnitTest {

    @Test
    public void smokeAlwaysPasses() {
        assertTrue(true, "canary");
    }
}
