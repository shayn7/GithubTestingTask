package dev.naamad.pages;

import dev.naamad.steps.BaseSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewRepositoryPage extends BasePage{

    public NewRepositoryPage(BaseSteps baseSteps) {
        super(baseSteps);
    }

    @Override
    public boolean verifyPage() {
        baseSteps.sleep(2);
        return baseSteps.getPageTitle().equals("New repository");
    }

    public void createRepository(String repositoryName) {
        baseSteps.setText(repoNameField, repositoryName);
        baseSteps.clickOnElement(createRepoButton);
    }

    @FindBy(css = "input#\\:r3\\:")
    private WebElement repoNameField;
    @FindBy(css = "button.types__StyledButton-sc-ws60qy-0.jUHnzz")
    private WebElement createRepoButton;
}
