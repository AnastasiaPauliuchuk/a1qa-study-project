package vk.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import vk.Message;
import vk.UserAccount;
import vk.forms.conversation.MessagesHistoryList;
import vk.menu.VKMessagesMenu;
import vk.utils.LocalManager;
import webdriver.elements.Label;

import java.time.Duration;
import java.time.LocalDateTime;


/**
 * @author Anastasia Pauliuchuk
 *         created:  11/9/2017.
 */
public class VKMyMessagesPage extends VKInnerPage {

    private static final String LBL_MSG_BLOCK_TEMPLATE = "//li[@data-list-id=\"%s\"]";
    private static final String MSG_USERNAME_LOC = "span._im_dialog_link";
    private static final String MSG_DATE_LOC = ".//div[contains(@class,\"nim-dialog--date\")]";
    private static final int TIME_DELTA_MIN = 5;
    private static final String UNREAD_STYLE = "nim-dialog_unread";
    public VKMessagesMenu messagesMenu = new VKMessagesMenu(By.cssSelector("div.im-right-menu"), "messages menu");
    private MessagesHistoryList messagesHistoryList = new MessagesHistoryList(By.xpath("//div[contains(@class,\"im-page-history-w\")]"), "history");
    private Label lblDialogs = new Label(By.id("im_dialogs"), "dialogs list");

    public VKMyMessagesPage() {
        super(By.cssSelector("div.im-page"), "VK My Messages Page");
    }

    public void assertMsgExists(UserAccount user) {
        assertConversationsIsPresent();
        Label lblMessageBlock = new Label(By.xpath(String.format(LBL_MSG_BLOCK_TEMPLATE, user.getId())), "msg block");
        assert (lblMessageBlock.isVisible());
    }

    public void assertMsgNotExists(UserAccount user) {
        assertConversationsIsPresent();
        Label lblMessageBlock = new Label(By.xpath(String.format(LBL_MSG_BLOCK_TEMPLATE, user.getId())), "msg block");
        assert (!lblMessageBlock.isPresent());
    }

    private void assertMsgAsConversation(Message msgSent, UserAccount user) {
        assertConversationsIsPresent();
        Label lblMessageBlock = new Label(By.xpath(String.format(LBL_MSG_BLOCK_TEMPLATE, user.getId())), "msg block");
        assert (lblMessageBlock.isVisible());

        String username = lblMessageBlock.getElement().findElement(By.cssSelector(MSG_USERNAME_LOC)).getText();
        String msgUsername = user.getLocalFullname();
        assertEquals(username, msgUsername);
        LocalDateTime dateTime = LocalManager.getInstance().buildDateFromString(lblMessageBlock.getElement().findElement(By.xpath(MSG_DATE_LOC)).getText());

        long wait = Duration.between(dateTime, msgSent.getDate()).toMinutes();
        Assert.assertTrue(wait < TIME_DELTA_MIN);

    }

    public void assertUnreadMsg(Message msgSent, UserAccount user) {
        assertConversationsIsPresent();
        Label lblMessageBlock = new Label(By.xpath(String.format(LBL_MSG_BLOCK_TEMPLATE, user.getId())), "msg block");
        assert (lblMessageBlock.isVisible());
        String cssClass = lblMessageBlock.getElement().getAttribute("class");
        assert (cssClass.contains(UNREAD_STYLE));
    }

    public void assertReadMsg(Message msgSent, UserAccount user) {
        assertConversationsIsPresent();
        Label lblMessageBlock = new Label(By.xpath(String.format(LBL_MSG_BLOCK_TEMPLATE, user.getId())), "msg block");
        assert (lblMessageBlock.isVisible());
        String cssClass = lblMessageBlock.getElement().getAttribute("class");
        assert (!cssClass.contains(UNREAD_STYLE));
    }

    public void assertSentMsgAsConversation(Message msgSent) {
        assertConversationsIsPresent();
        assertMsgAsConversation(msgSent, msgSent.getRecipient());
    }

    public void assertReceivedMsgAsConversation(Message msgSent) {
        assertConversationsIsPresent();
        assertMsgAsConversation(msgSent, msgSent.getSender());
    }

    public void goConversation(String userID) {
        String loc = String.format(LBL_MSG_BLOCK_TEMPLATE, userID);
        Label lblMessageBlock = new Label(By.xpath(loc), "msg block");
        lblMessageBlock.click();
    }

    public void assertSentMsgTextInList(Message msgSent) {
        messagesHistoryList.assertLastSentMsgText(msgSent);
    }

    public void assertLastSentMsgDetails(Message msgSent) {
        messagesHistoryList.assertLastSentMsgDetails(msgSent);
    }

    public Message replyMessage(Message msgSent) {
        return messagesHistoryList.reply(msgSent);
    }

    public void assertMsgWithQuote(Message msgSent, Message msgReply) {
        messagesHistoryList.assertMsgWithQuote(msgSent, msgReply);
    }

    public void assertConversationsIsPresent() {
        assert (lblDialogs.isVisible());
    }

    public String getHistoryContent() {
        return messagesHistoryList.getElement().getText();
    }

    public void waitHistoryChanged(String history) {
        assert (messagesHistoryList.isContentChanged(history));
    }


    public void assertOrder(Message msgSent, Message msgReceived) {
        messagesHistoryList.assertOrder(msgSent, msgReceived);
    }
}
