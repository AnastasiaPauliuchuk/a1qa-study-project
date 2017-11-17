package vk.tests;

import org.testng.annotations.BeforeTest;
import vk.Message;
import vk.UserAccount;
import vk.pages.VKHomePage;
import vk.pages.VKInnerPage;
import vk.utils.UserManager;
import webdriver.BaseTest;

import java.util.ArrayList;

import static vk.tests.VKTestMethods.sendMessage;
import static vk.tests.VKTestMethods.testReceiveMessage;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/10/2017.
 */
public class VKReceiveTest extends BaseTest {

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

        logger.step(0, String.format(user1.getLocalFullname() + " goes to " + user2.getLocalFullname() + " page and send a message"));
        Message msgSent = sendMessage(vkHomePage, user1, user2);
        VKInnerPage vkInnerPage = new VKInnerPage();
        vkInnerPage.logout();
        testReceiveMessage(vkInnerPage, user2, msgSent);
    }


}
