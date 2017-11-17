package vk.tests;

import vk.Message;
import vk.UserAccount;
import vk.menu.VKMainMenu;
import vk.menu.VKMessagesMenu;
import vk.pages.VKAccountPage;
import vk.pages.VKMyMessagesPage;
import vk.pages.VKPage;
import vk.pages.VKUserAccountPage;
import webdriver.BaseEntity;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/10/2017.
 */
public class VKTestMethods extends BaseEntity {

    public static Message sendMessage(VKPage startPage, UserAccount userSender, UserAccount userRecipient) {

        startPage.goToURL(userRecipient.getPageURL());
        logger.info("[go to recipient's page]");
        VKUserAccountPage vkAccountPage = new VKUserAccountPage();
        vkAccountPage.login(userSender);
        logger.info("[login");
        vkAccountPage.openMsgForm();
        logger.info("[write a message in popup form]");
        Message msgSent = vkAccountPage.sendMessageInPopup();
        msgSent.setSender(userSender);
        msgSent.setRecipient(userRecipient);


        return msgSent;
    }

    public static Message testSendMessage(VKPage startPage, UserAccount userSender, UserAccount userRecipient) {

        logger.step(1, String.format("Go to recipient's page and send a message"));
        Message msgSent = sendMessage(startPage, userSender, userRecipient);

        logger.step(2, String.format("Check popup window displayed"));
        VKUserAccountPage vkUser2Page = new VKUserAccountPage();
        vkUser2Page.assertPopupSent();
        logger.step(3, String.format("Check the message sent displayed on Messages page"));


        VKAccountPage vkAccountPage = new VKAccountPage();
        vkAccountPage.getMainMenu().selectItem(VKMainMenu.Items.MESSAGES);


        VKMyMessagesPage vkMyMessagesPage = new VKMyMessagesPage();
        vkMyMessagesPage.messagesMenu.selectItem(VKMessagesMenu.Items.ALLMESSAGES);
        logger.step(4, String.format("Check the message details"));
        vkMyMessagesPage.assertSentMsgAsConversation(msgSent);
        logger.step(5, String.format("Check the message displayed unread-styled"));
        vkMyMessagesPage.assertUnreadMsg(msgSent, userRecipient);

        vkMyMessagesPage.goConversation(userRecipient.getId());
        logger.step(6, String.format("Check the message details displayed in conversation with " + userRecipient.getLocalFullname() + " history"));
        vkMyMessagesPage.assertSentMsgTextInList(msgSent);

        return msgSent;
    }

    public static void testReceiveMessage(VKPage startPage, UserAccount userRecipient, Message msgSent) {

        logger.step(1, "Login");
        startPage.login(userRecipient);
        logger.step(2, "Check new unread messages label");
        VKAccountPage vkAccountPage = new VKAccountPage();
        vkAccountPage.assertMsgCountPresent();
        logger.step(3, "Check the message sent displayed on Messages page");
        assert (vkAccountPage.getMainMenu().isVisible());
        vkAccountPage.getMainMenu().selectItem(VKMainMenu.Items.MESSAGES);
        VKMyMessagesPage vkMyMessagesPage = new VKMyMessagesPage();


        logger.step(4, String.format("Check the message details"));
        vkMyMessagesPage.assertReceivedMsgAsConversation(msgSent);
        logger.step(5, "Check the message displayed unread-styled");
        vkMyMessagesPage.assertUnreadMsg(msgSent, msgSent.getSender());
        logger.step(6, "Check the message sent displayed on Messages/Unread page");
        vkMyMessagesPage.messagesMenu.selectItem(VKMessagesMenu.Items.UNREAD);
        logger.step(7, String.format("Check the message details"));
        vkMyMessagesPage.assertReceivedMsgAsConversation(msgSent);
        logger.step(8, "Check the message displayed unread-styled");
        vkMyMessagesPage.assertUnreadMsg(msgSent, msgSent.getSender());


    }

    public static void testReadMessageForSender(VKPage startPage, Message msgSent) {

        startPage.login(msgSent.getSender());
        logger.step(1, "Check the message sent displayed on Messages page");
        VKAccountPage vkAccountPage = new VKAccountPage();
        vkAccountPage.getMainMenu().selectItem(VKMainMenu.Items.MESSAGES);
        VKMyMessagesPage vkMyMessagesPage = new VKMyMessagesPage();

        logger.step(2, "Check the message displayed read-styled");
        vkMyMessagesPage.assertMsgExists(msgSent.getRecipient());
        vkMyMessagesPage.assertReadMsg(msgSent, msgSent.getRecipient());

    }

    public static void readTheSentMessage(VKAccountPage startPage, Message msgSent) {


        startPage.getMainMenu().selectItem(VKMainMenu.Items.MESSAGES);
        VKMyMessagesPage vkMyMessagesPage = new VKMyMessagesPage();
        vkMyMessagesPage.goConversation(msgSent.getSender().getId());
    }

    public static void testReadMessageForRecipient(VKPage startPage, Message msgSent) {
        startPage.login(msgSent.getRecipient());

        logger.step(1, "Check the message sent displayed on Messages page");
        VKAccountPage vkAccountPage = new VKAccountPage();


        vkAccountPage.getMainMenu().selectItem(VKMainMenu.Items.MESSAGES);
        VKMyMessagesPage vkMyMessagesPage = new VKMyMessagesPage();
        vkMyMessagesPage.assertMsgExists(msgSent.getSender());
        logger.step(2, "Check the message displayed unread-styled");
        vkMyMessagesPage.assertUnreadMsg(msgSent, msgSent.getSender());

        logger.step(3, "Go to unread messages");
        vkMyMessagesPage.messagesMenu.selectItem(VKMessagesMenu.Items.UNREAD);
        vkMyMessagesPage.assertMsgExists(msgSent.getSender());
        vkMyMessagesPage.assertUnreadMsg(msgSent, msgSent.getSender());

        logger.step(4, "Read the message");
        vkMyMessagesPage.goConversation(msgSent.getSender().getId());

        logger.step(5, "Check the message displayed read-styled");
        vkMyMessagesPage.messagesMenu.selectItem(VKMessagesMenu.Items.ALLMESSAGES);
        vkMyMessagesPage.assertReadMsg(msgSent, msgSent.getSender());

        logger.step(6, "Check message not exists in Unread");
        vkMyMessagesPage.messagesMenu.selectItem(VKMessagesMenu.Items.UNREAD);
        vkMyMessagesPage.assertMsgNotExists(msgSent.getSender());


        logger.step(7, "Check new unread messages label not exists");
        vkAccountPage.assertMsgCountNotPresent();
    }

    public static void testConversationHistory(VKPage startPage, Message msgSent, Message msgReceived) {

        logger.step(1, "Go to recipient's page");
        startPage.goToURL(msgSent.getRecipient().getPageURL());
        startPage.login(msgSent.getSender());
        VKUserAccountPage vkAccountPage = new VKUserAccountPage();
        logger.step(2, "Open Write message pupup window");
        vkAccountPage.openMsgForm();
        logger.step(3, "Go to conversation history");
        vkAccountPage.goHistory();
        VKMyMessagesPage vkMyMessagesPage = new VKMyMessagesPage();
        logger.step(4, "Check the message details");
        vkMyMessagesPage.assertLastSentMsgDetails(msgReceived);
        logger.step(5, "Check the message details");
        vkMyMessagesPage.assertLastSentMsgDetails(msgSent);
        logger.step(6, "Check messages order");
        vkMyMessagesPage.assertOrder(msgSent, msgReceived);

    }

    public static Message replyMessage(VKPage startPage, Message msgSent) {

        startPage.login(msgSent.getRecipient());
        VKAccountPage vkAccountPage = new VKAccountPage();
        readTheSentMessage(vkAccountPage, msgSent);
        VKMyMessagesPage vkMyMessagesPage = new VKMyMessagesPage();
        String history = vkMyMessagesPage.getHistoryContent();
        Message msgReply = vkMyMessagesPage.replyMessage(msgSent);
        vkMyMessagesPage.waitHistoryChanged(history);
        return msgReply;
    }

    @Override
    protected String formatLogMsg(String message) {
        return message;
    }
}
