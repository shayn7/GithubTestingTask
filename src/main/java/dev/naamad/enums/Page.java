package dev.naamad.enums;

import dev.naamad.pages.BasePage;
import dev.naamad.pages.HomePage;
import dev.naamad.pages.LoginPage;
import dev.naamad.pages.NewRepositoryPage;
import dev.naamad.steps.BaseSteps;

public enum Page {

    HOME_PAGE(){
        @Override
        public BasePage getPage(BaseSteps baseSteps) {
            return new HomePage(baseSteps);
        }
    },
    NEW_REPOSITORY_PAGE(){
        @Override
        public BasePage getPage(BaseSteps baseSteps) {
            return new NewRepositoryPage(baseSteps);
        }
    },
    LOGIN_PAGE(){
        @Override
        public BasePage getPage(BaseSteps baseSteps) {
            return new LoginPage(baseSteps);
        }
    };

    public abstract BasePage getPage(BaseSteps baseSteps);
}
