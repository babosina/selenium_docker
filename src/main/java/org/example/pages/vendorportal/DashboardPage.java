package org.example.pages.vendorportal;

import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DashboardPage extends BasePage {

    public static final Logger LOGGER = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarning;

    @FindBy(id = "annual-earning")
    private WebElement annualEarning;

    @FindBy(id = "profit-margin")
    private WebElement profitMargin;

    @FindBy(id = "available-inventory")
    private WebElement availableInventory;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement dataTableInfo;

    @FindBy(id = "userDropdown")
    private WebElement userDropdown;

    @FindBy(css = "a[data-toggle='modal']")
    private WebElement profileLogoutButton;

    @FindBy(css = "#logoutModal a")
    private WebElement logoutButtonModal;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarning));
        return this.monthlyEarning.isDisplayed();
    }

    public String getMonthlyEarning() {
        return this.monthlyEarning.getText();
    }

    public String getAnnualEarning() {
        return this.annualEarning.getText();
    }

    public String getProfitMargin() {
        return this.profitMargin.getText();
    }

    public String getAvailableInventory() {
        return this.availableInventory.getText();
    }

    public void searchOrderHistory(String keyword) {
        this.searchInput.clear();
        this.searchInput.sendKeys(keyword);
    }

    public int getResultsCount() {
        String infoText = this.dataTableInfo.getText();
        String[] parts = infoText.split(" ");
        LOGGER.info("Total results: {}", parts[5]);
        return Integer.parseInt(parts[5]);
    }

    public void logout() {
        this.userDropdown.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.profileLogoutButton));
        this.profileLogoutButton.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutButtonModal));
        this.logoutButtonModal.click();
    }

}
