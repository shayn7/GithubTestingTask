package dev.naamad.pages;

import dev.naamad.steps.BaseSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(BaseSteps baseSteps) {
        super(baseSteps);
    }

    @Override
    public boolean verifyPage() {
        return baseSteps.getPageTitle().equals("Sign in to GitHub · GitHub");
    }

    public void login(String userName, String password) {
        baseSteps.setText(loginField, userName);
        baseSteps.setText(passwordField, password);
        baseSteps.clickOnElement(signInButton);
    }

    @FindBy(id = "login_field")
    private WebElement loginField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(name = "commit")
    private WebElement signInButton;
}
