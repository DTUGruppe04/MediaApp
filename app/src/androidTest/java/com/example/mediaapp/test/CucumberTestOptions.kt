package com.example.mediaapp.test

import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(features = ["features"],
    glue = ["com.example.mediaapp.test"])
class CucumberTestOptions{

}