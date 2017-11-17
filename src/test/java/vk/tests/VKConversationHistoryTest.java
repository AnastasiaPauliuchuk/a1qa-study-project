package vk.tests;

import org.testng.annotations.BeforeTest;
import vk.Message;
import vk.UserAccount;
import vk.pages.VKHomePage;
import vk.pages.VKInnerPage;
import vk.pages.VKUserAccountPage;
import vk.utils.UserManager;
import webdriver.BaseTest;

import java.util.ArrayList;

import static vk.tests.VKTestMethods.sendMessage;
import static vk.tests.VKTestMethods.testConversationHistory;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/12/2017.
 */
public class VKConversationHistoryTest extends BaseTest {

    private static UserAccount user1;
    private static UserAccount user2;

    @BeforeTest
    private void setUp() {
        ArrayList<UserAccount> users = UserManager.createUserAccountsForMessaging();
        user1 = users.get(0);
        user2 = users.get(1);
    }

    @Override
    public void runTest() {

        VKHomePage vkHomePage = new VKHomePage();
        Message msgSent1 = sendMessage(vkHomePage, user2, user1);
        VKInnerPage vkInnerPage = new VKInnerPage();
        vkInnerPage.logout();
        Message msgSent2 = sendMessage(vkInnerPage, user1, user2);
        VKUserAccountPage vkUserAccountPage = new VKUserAccountPage();
        vkUserAccountPage.logout();

        testConversationHistory(vkUserAccountPage, msgSent1, msgSent2);


    }
}
