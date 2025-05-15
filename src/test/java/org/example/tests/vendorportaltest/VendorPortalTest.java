package org.example.tests.vendorportaltest;

import org.example.pages.vendorportal.DashboardPage;
import org.example.pages.vendorportal.LoginPage;
import org.example.tests.BaseTest;
import org.example.tests.vendorportaltest.model.VendorPortalTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.Config;
import util.Constants;
import util.JSONUtil;

public class VendorPortalTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void initPageObjects(String testDataPath) {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JSONUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }


    @Test
    public void testLogin() {
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());

        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "testLogin")
    public void testDashboard() {
        Assert.assertTrue(dashboardPage.isAt());

        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        dashboardPage.searchOrderHistory(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getResultsCount(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "testDashboard")
    public void testLogout() {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
