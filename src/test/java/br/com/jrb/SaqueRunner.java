package br.com.jrb;



import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    glue = "br.com.jrb.steps",
    plugin = {"pretty"}
)

public class SaqueRunner { }
