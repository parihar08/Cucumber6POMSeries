package stepdefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.pages.AccountsPage;
import com.pages.LoginPage;
import com.qa.factory.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AccountsPageSteps {
	
	private LoginPage loginPage =new LoginPage(DriverFactory.getDriver());
	private AccountsPage accountsPage;
	
	@Given("^user has already logged in to application$")
	public void user_has_already_logged_in_to_application(DataTable credTable) {
		
		List<Map<String,String>> credList=credTable.asMaps();
		String username = credList.get(0).get("username");
		String password = credList.get(0).get("password");
		DriverFactory.getDriver().get("http://automationpractice.com/index.php?"
				+ "controller=authentication&back=my-account");
		accountsPage = loginPage.doLogin(username, password);
		
	}

	@Given("^user is on Accounts page$")
	public void user_is_on_Accounts_page()  {
		String title = accountsPage.getAccountsPageTitle();
		System.out.println("Accounts Page Title is: "+title);
	}

	@Then("^user gets accounts section$")
	public void user_gets_accounts_section(DataTable sectionsTable) {
		List<String> expAccountSectionsList = sectionsTable.asList();
		System.out.println("Expected Accounts Section List "+expAccountSectionsList);
		
		List<String> actualAccountSectionsList = accountsPage.getAccountsSectionsList();
		System.out.println("Actual Accounts Section List "+actualAccountSectionsList);
		
		Assert.assertTrue(expAccountSectionsList.containsAll(actualAccountSectionsList));
	}

	@Then("^accounts section count should be (\\d+)$")
	public void accounts_section_count_should_be(int expectedSectionCount) {
		Assert.assertTrue(accountsPage.getAccountSectionCount() == expectedSectionCount);
	}


}
