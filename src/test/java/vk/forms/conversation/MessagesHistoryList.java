package vk.forms.conversation;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import vk.Message;
import vk.utils.LocalManager;
import vk.utils.MessageBuilder;
import webdriver.elements.BaseElement;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/9/2017.
 */
public class MessagesHistoryList extends BaseElement {


    private final static String LIST_SELECTOR_TEMPLATE =
            "//div[contains(@class,\"im-mess-stack\")]/a[@href=\"/id%s\"]/../../following-sibling::ul/li";
    private final static String ITEM_SELECTOR_TEMPLATE =
            "(//div[contains(@class,\"im-mess-stack\")]/a[@href=\"/id%1$s\"]/../../following-sibling::ul/li)[%2$s]";
    private final static By MSG_TEXT_LOC = By.cssSelector("div.im-mess--text");
    private final static By MSG_NAME_LOC = By.xpath("../preceding-sibling::div//a[contains(@class,\"im-mess-stack--lnk\")]");
    private final static By MSG_TIME_LOC = By.xpath("../preceding-sibling::div//a[contains(@class,\"_im_mess_link\")]");

    private final static String QUOTE_LOC = "//div[contains(@class,\"im_fwd_log_wrap\")]";
    private final static String QUOTE_NAME_LOC = "//a[contains(@class,\"im-mess-stack--lnk\")]";
    private final static String QUOTE_TIME_LOC = "//span[@class=\"im-mess-stack--tools\"]";

    private final static String MSG_REPLY_LOC = "//span[contains(@class,\"im-mess--reply\")]";
    private static final int TIME_DELTA_MIN = 5;
    private static final int maxMsgLength = 200;
    private TextBox tbMsg = new TextBox(By.cssSelector("div.im-chat-input--text"), "message input");

    public MessagesHistoryList(By loc, String nameOf) {
        super(loc, nameOf);
    }


    public Label getLastMsgFromUser(String userId) {

        String loc = String.format(LIST_SELECTOR_TEMPLATE, userId);
        List<WebElement> list = this.getElement().findElements(By.xpath(loc));
        Label lastMsg = new Label(By.xpath(String.format(ITEM_SELECTOR_TEMPLATE, userId, list.size())), "item#" + list.size());
        assert (lastMsg.isVisible());
        return lastMsg;
    }

    @Override
    public String getElementType() {
        return getLoc("loc.list");
    }

    public void assertLastSentMsgText(Message msgSent) {

        String msg = getLastMsgFromUser(msgSent.getSender().getId()).getElement().findElement(MSG_TEXT_LOC).getText();
        assertEquals(msg, msgSent.getMsgText().trim());
    }

    public void assertLastSentMsgDetails(Message msgSent) {

        Label lblLastMsg = getLastMsgFromUser(msgSent.getSender().getId());
        assert (lblLastMsg.isVisible());
        WebElement lblLastMsgElem = lblLastMsg.getElement();
        String msg = lblLastMsgElem.findElement(MSG_TEXT_LOC).getAttribute("innerHTML");
        String username = lblLastMsgElem.findElement(MSG_NAME_LOC).getText();


        assert (msg.contains(msgSent.getMsgText().trim()));
        String msgUsername = msgSent.getSender().getLocalName();
        assertEquals(username, msgUsername);

        LocalDateTime dateTime = LocalManager.getInstance().buildDateFromString(lblLastMsgElem.findElement(MSG_TIME_LOC).getText());
        long wait = Duration.between(dateTime, msgSent.getDate()).toMinutes();
        assert (wait < TIME_DELTA_MIN);

    }


    public Message reply(Message msgSent) {

        Label lblLastMsg = getLastMsgFromUser(msgSent.getSender().getId());
        lblLastMsg.hover();
        String xpath = lblLastMsg.getLocator().toString().replace("By.xpath: ", "");
        Label lblReply = new Label(By.xpath(xpath + MSG_REPLY_LOC), "reply label");
        lblReply.click();
        String txtReply = MessageBuilder.buildMessage(maxMsgLength);
        tbMsg.type(txtReply);
        tbMsg.submitPressingReturn();
        return new Message(txtReply, msgSent.getRecipient(), msgSent.getSender(), LocalDateTime.now());

    }


    public void assertMsgWithQuote(Message msgSent, Message msgReply) {

        assertLastSentMsgDetails(msgReply);

        Label lblLastMsg = getLastMsgFromUser(msgSent.getRecipient().getId());
        Label lblQuote = new Label(By.xpath(lblLastMsg.getLocator().toString().replace("By.xpath: ", "") + QUOTE_LOC));
        assert (lblQuote.isVisible());
        String msg = lblQuote.getText();
        assert (msg.contains(msgSent.getMsgText()));

        Label lblQuoteUsername = new Label(By.xpath(lblQuote.getLocator().toString().replace("By.xpath: ", "") + QUOTE_NAME_LOC));
        String username = lblQuoteUsername.getText();
        String msgUsername = msgSent.getSender().getLocalName();
        assertEquals(username, msgUsername);

        Label lblQuoteTime = new Label(By.xpath(lblQuote.getLocator().toString().replace("By.xpath: ", "") + QUOTE_TIME_LOC));

        LocalDateTime dateTime = LocalManager.getInstance().buildDateFromString(lblQuoteTime.getText());

        long wait = Duration.between(dateTime, msgSent.getDate()).toMinutes();
        assert (wait < TIME_DELTA_MIN);


    }


    public void assertOrder(Message msgSent, Message msgReceived) {
        Label lblLastMsgSent = getLastMsgFromUser(msgSent.getSender().getId());
        Label lblLastMsgReceived = getLastMsgFromUser(msgReceived.getSender().getId());
        Point p1 = lblLastMsgSent.getElement().getLocation();
        Point p2 = lblLastMsgReceived.getElement().getLocation();
        assert (p2.getY() > p1.getY());
    }
}
