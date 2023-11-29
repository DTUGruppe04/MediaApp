package com.dtu.uemad.cucumbertest.test

import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(features = ["features"],
    glue = ["java.com.dtu.uemad.cucumbertest"])
class CucumberTestOptions : CucumberAndroidJUnitRunner() {

}