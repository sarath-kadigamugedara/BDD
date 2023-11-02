/*This file includes the test case to test the functionality
of creating a new user in the specified web application. 
It verifies that the new user is created successfully an it 
is displayed in the search results*/
package com.actitime.qa.testcases;

import com.actitime.qa.base.TestBase;
import com.actitime.qa.pages.HomePage;
import com.actitime.qa.pages.LoginPage;
import com.actitime.qa.pages.UsersPage;
import com.actitime.qa.testdata.TestData;
import org.apache.poi.ss.formula.functions.T;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateUserTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    UsersPage userPage;
    SoftAssert softAssert;
    public CreateUserTest() {
        super();

    }
    @BeforeMethod
    public void setup() throws InterruptedException {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        userPage = new UsersPage();
        softAssert = new SoftAssert();
        homePage = loginPage.loging(properties.getProperty("username"), properties.getProperty("password"));
    }

    //tests the creation of the new user
    @Test
    public void createUser() throws InterruptedException {
        //clicks on the "Users" link on the homepage
        homePage.clickOnUsersLink(); 
        //Clicks on the "New User" button
        userPage.clickOnNewUser(); 
        //Enters the user's first name, last name, email and department into the fields
        userPage.enterNewUserDetails(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.EMAIL, TestData.DEPARTMENT); 
        //Clicks the "Close" button to save the new user
        userPage.clickNewUserClose(); 
        //Searches for the new user by their first name and last name
        userPage.searchForUser(TestData.FIRST_NAME, TestData.LAST_NAME); 
        //Verifies that the new user is displayed in the search results. If it not displayed, the test fails
        softAssert.assertTrue(userPage.checkIfSearchedNameIsDisplayed(TestData.FIRST_NAME, TestData.LAST_NAME), "The Created User is not displayed"); 
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
