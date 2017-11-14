package vk.tests;

import org.testng.annotations.BeforeTest;
import vk.Message;
import vk.UserAccount;
import vk.pages.VKHomePage;
import vk.pages.VKInnerPage;
import vk.pages.VKMyMessagesPage;
import webdriver.BaseTest;
import webdriver.PropertiesResourceManager;

import static vk.tests.VKTestMethods.replyMessage;
import static vk.tests.VKTestMethods.sendMessage;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/12/2017.
 */
public class VKSendMessageWithQuoteTest extends BaseTest {
    private static String testDataFile = "vkTestData.properties";
    private static UserAccount user1;
    private static UserAccount user2;

    @BeforeTest
    private void setUp() {
        PropertiesResourceManager prop = new PropertiesResourceManager(testDataFile);
        user1 = new UserAccount(prop.getProperty("user1.login"), prop.getProperty("user1.password"), prop.getProperty("user1.id"), prop.getProperty("user1.lang"));
        user2 = new UserAccount(prop.getProperty("user2.login"), prop.getProperty("user2.password"), prop.getProperty("user2.id"), prop.getProperty("user2.lang"));
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
