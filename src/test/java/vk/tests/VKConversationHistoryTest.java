package vk.tests;

import org.testng.annotations.BeforeTest;
import vk.Message;
import vk.UserAccount;
import vk.pages.VKHomePage;
import vk.pages.VKInnerPage;
import vk.pages.VKUserAccountPage;
import webdriver.BaseTest;
import webdriver.PropertiesResourceManager;

import static vk.tests.VKTestMethods.sendMessage;
import static vk.tests.VKTestMethods.testConversationHistory;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/12/2017.
 */
public class VKConversationHistoryTest extends BaseTest {

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
        Message msgSent1 = sendMessage(vkHomePage, user2, user1);
        VKInnerPage vkInnerPage = new VKInnerPage();
        vkInnerPage.logout();
        Message msgSent2 = sendMessage(vkInnerPage, user1, user2);
        VKUserAccountPage vkUserAccountPage = new VKUserAccountPage();
        vkUserAccountPage.logout();

        testConversationHistory(vkUserAccountPage, msgSent1, msgSent2);


    }
}
