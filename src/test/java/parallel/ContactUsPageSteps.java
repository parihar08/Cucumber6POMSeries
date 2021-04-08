package parallel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;

import com.pages.ContactUsPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ExcelReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactUsPageSteps {
	
	private ContactUsPage contactUsPage= new ContactUsPage(DriverFactory.getDriver());
	
	
	@Given("^user navigates to contact us page$")
	public void user_navigates_to_contact_us_page()  {
	    DriverFactory.getDriver().get("http://automationpractice.com/index.php?"
				+ "controller=contact");
	}

	@When("^user fills the form from given sheetname \"([^\"]*)\" and rownumber (\\d+)$")
	public void user_fills_the_form_from_given_sheetname_and_rownumber(String sheetName, int rowNum)  {
	    ExcelReader reader = new ExcelReader();
	    try {
			List<Map<String,String>> testData= 
					reader.getData("/Users/Parihar08/Documents/workspace/CucumberPOMSeriesNaveen"
					+ "/src/test/resources/config/automation.xlsx", sheetName);
			String heading  = testData.get(rowNum).get("subjectheading");
			String email  = testData.get(rowNum).get("email");
			String orderRef  = testData.get(rowNum).get("orderref");
			String message  = testData.get(rowNum).get("message");
			
			contactUsPage.fillContactUsForm(heading, email, orderRef, message);
			
	    } 
	    catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@When("^user clicks on send button$")
	public void user_clicks_on_send_button()  {
		contactUsPage.clickSend();
	}

	@Then("^it shows a successful message \"([^\"]*)\"$")
	public void it_shows_a_successful_message(String expectedSuccessMessage) {
	    String actualSuccessMessage = contactUsPage.getSuccessMessg();
	    Assert.assertEquals(expectedSuccessMessage, actualSuccessMessage);
	}


}
