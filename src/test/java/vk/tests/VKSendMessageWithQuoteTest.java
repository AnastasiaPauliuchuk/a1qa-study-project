package vk.tests;

import org.testng.annotations.BeforeTest;
import vk.Message;
import vk.UserAccount;
import vk.pages.VKHomePage;
import vk.pages.VKInnerPage;
import vk.pages.VKMyMessagesPage;
import vk.utils.UserManager;
import webdriver.BaseTest;

import java.util.ArrayList;

import static vk.tests.VKTestMethods.replyMessage;
import static vk.tests.VKTestMethods.sendMessage;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/12/2017.
 */
public class VKSendMessageWithQuoteTest extends BaseTest {
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
        Message msgSent = sendMessage(vkHomePage, user1, user2);
        VKInnerPage vkInnerPage = new VKInnerPage();
        vkInnerPage.logout();


        logger.step(1, "Choose the message and reply");
        Message msgReply = replyMessage(vkInnerPage, msgSent);

        VKMyMessagesPage vkMyMessagesPage = new VKMyMessagesPage();
        logger.step(2, "Check the message and the answer details");
        vkMyMessagesPage.assertMsgWithQuote(msgSent, msgReply);
    }


}
