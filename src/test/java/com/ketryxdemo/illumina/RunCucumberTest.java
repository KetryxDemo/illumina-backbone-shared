package com.ketryxdemo.illumina;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

/**
 * Bridge class so Surefire (which scans compiled Java classes) can trigger
 * the cucumber-junit-platform-engine to run all .feature files under
 * src/test/resources/features/.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.ketryxdemo.illumina.steps")
public class RunCucumberTest {
}
