package dev.naamad.pages;

import dev.naamad.steps.BaseSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class HomePage extends BasePage{

    public HomePage(BaseSteps baseSteps) {
        super(baseSteps);
    }

    @Override
    public boolean verifyPage() {
        return baseSteps.getPageTitle().equals("GitHub");
    }

    public void goToNewRepositoryPage() {
        baseSteps.clickOnElement(createMenuButton);
        Optional<WebElement> newRepositoryOption = createMenuList.stream()
                .filter(e -> e.getText().trim().equals("New repository"))
                .findAny();

        if (newRepositoryOption.isEmpty()) throw new RuntimeException("creating new repository option not present in the menu");
        newRepositoryOption.get().click();
    }

    @FindBy(css = "button#global-create-menu-button")
    private WebElement createMenuButton;
    @FindBy(css = "span.ActionListItem-label")
    private List<WebElement> createMenuList;
}
