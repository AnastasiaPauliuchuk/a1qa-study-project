package vk.forms.conversation;

import org.openqa.selenium.By;
import vk.Message;
import vk.utils.MessageBuilder;
import webdriver.elements.BaseElement;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

import java.time.LocalDateTime;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/9/2017.
 */
public class WriteMessagePopup extends BaseElement {

    private static final int msgLength = 150;
    private TextBox tbMsg = new TextBox(By.id("mail_box_editable"), "msg textbox");
    private Button btnSend = new Button(By.id("mail_box_send"), "btn send");


    public WriteMessagePopup(By loc, String s) {
        super(loc, s);
    }

    @Override
    protected String getElementType() {
        return getLoc("loc.popup");
    }

    public Message sendMessage() {
        String msgText = MessageBuilder.buildMessage(msgLength);
        tbMsg.type(msgText);
        btnSend.click();
        Message msg = new Message(msgText, LocalDateTime.now());
        return msg;
    }


}
