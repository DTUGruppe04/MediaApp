package com.example.mediaapp.cucumber

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith
@RunWith(Cucumber::class)
@CucumberOptions(glue = ["com.example.mediaapp.cucumber"])
class RunCucumberTest {

}