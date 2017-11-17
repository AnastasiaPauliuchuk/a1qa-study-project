package vk.tests;

import org.testng.annotations.BeforeTest;
import vk.UserAccount;
import vk.pages.VKHomePage;
import vk.pages.VKPage;
import vk.utils.UserManager;
import webdriver.BaseTest;

import java.util.ArrayList;

import static vk.tests.VKTestMethods.testSendMessage;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/8/2017.
 */
public class VKSendTest extends BaseTest {

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

        VKPage vkHomePage = new VKHomePage();
        testSendMessage(vkHomePage, user2, user1);

    }

}
