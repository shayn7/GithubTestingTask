package dev.naamad.tests;

import dev.naamad.pages.HomePage;
import dev.naamad.pages.LoginPage;
import dev.naamad.pages.NewRepositoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.util.UUID;

import static dev.naamad.enums.Page.*;

public class RepositoryCreationTests extends TestSetup{


    @Test(description = "validate repository creation")
    public void testCreateRepository() throws MalformedURLException {
        baseSteps.startSession();
        baseSteps.navigateToUrl(baseUrl);
        baseSteps.iShouldBeOnPage(LOGIN_PAGE);
        LoginPage loginPage = baseSteps.getExpectedPage();
        loginPage.login(userName, password);
        baseSteps.iShouldBeOnPage(HOME_PAGE);
        HomePage homePage = baseSteps.getExpectedPage();
        homePage.goToNewRepositoryPage();
        baseSteps.iShouldBeOnPage(NEW_REPOSITORY_PAGE);
        //TODO: continue with the test logic and add assertions
    }

}
