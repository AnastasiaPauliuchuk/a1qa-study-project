package vk.utils;

import vk.UserAccount;
import webdriver.PropertiesResourceManager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/17/2017.
 */
public class UserManager {

    private static String TEST_DATE_FILE = "vkTestData.properties";
    private static String USER_1 = "user1";
    private static String USER_2 = "user2";


    public static UserAccount createUserAccount(String userPropPrefix) {
        PropertiesResourceManager prop = new PropertiesResourceManager(TEST_DATE_FILE);
        return new UserAccount(prop.getProperty(userPropPrefix + ".login"),
                prop.getProperty(userPropPrefix + ".password"),
                prop.getProperty(userPropPrefix + ".id"),
                prop.getProperty(userPropPrefix + ".lang"));
    }

    public static ArrayList<UserAccount> createUserAccountsForMessaging() {
        UserAccount user1 = createUserAccount(USER_1);
        UserAccount user2 = createUserAccount(USER_2);
        return new ArrayList(Arrays.asList(user1, user2));
    }
}
