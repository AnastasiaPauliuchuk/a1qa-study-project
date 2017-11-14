package vk.utils;

import java.util.Random;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/9/2017.
 */
public class MessageBuilder {

    public static final String messageProp = "messagepool";

    public static String buildMessage(int length) {

        String pool = LocalManager.getInstance().getLocalSetting(messageProp);
        if (length >= pool.length()) return pool;
        Random rn = new Random();

        int count = rn.nextInt(length - 1);
        int start = rn.nextInt(pool.length() - count);
        return pool.substring(start, start + count + 1);
    }
}
