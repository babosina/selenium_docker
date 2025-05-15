package org.example.pages.flightreservation;

import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends BasePage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;

    @FindBy(css = "p b")
    private WebElement userName;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public void goToFlightsSearch() {
        this.goToFlightsSearchButton.click();
    }

    public String getUserName() {
        return this.userName.getText();
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchButton));
        return this.goToFlightsSearchButton.isDisplayed();
    }
}
