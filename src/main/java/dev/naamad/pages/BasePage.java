package dev.naamad.pages;

import dev.naamad.steps.BaseSteps;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected BaseSteps baseSteps;

    public BasePage(BaseSteps baseSteps){
        this.baseSteps = baseSteps;
        PageFactory.initElements(baseSteps.getDriver(), this);
    }

    public abstract boolean verifyPage();
}
