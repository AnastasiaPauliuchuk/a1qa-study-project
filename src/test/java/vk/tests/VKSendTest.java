package vk.tests;

import org.testng.annotations.BeforeTest;
import vk.UserAccount;
import vk.pages.VKHomePage;
import vk.pages.VKPage;
import webdriver.BaseTest;
import webdriver.PropertiesResourceManager;

import static vk.tests.VKTestMethods.testSendMessage;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/8/2017.
 */
public class VKSendTest extends BaseTest {

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

        VKPage vkHomePage = new VKHomePage();
        testSendMessage(vkHomePage, user2, user1);

    }

}
