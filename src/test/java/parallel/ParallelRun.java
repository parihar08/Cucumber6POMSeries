package parallel;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//Runner for TestNG for running Scenarios in parallel mode
//In order to run in parallel mode using TestNG, step definitions, hooks and runner file
//should be saved in the same folder location (e.g. parallel)
//Also, feature files are also saved in parallel folder under src/test/resources
//Same information is provided in the Cucumber Option below

@RunWith(Cucumber.class)
@CucumberOptions(
		
		//features= {"src/test/resources/parallel"},
		plugin = {"pretty"
			//	 ,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					,"timeline:test-output-thread/"
						},
				monochrome = true,
		glue= {"parallel"},
		features= {"src/test/resources/parallel/ContactUs.feature"}
		)

public class ParallelRun extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
		
	}

}
