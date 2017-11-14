package vk.pages;

import org.openqa.selenium.By;
import vk.Message;
import vk.forms.conversation.WriteMessagePopup;
import webdriver.elements.Button;
import webdriver.elements.Label;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/12/2017.
 */
public class VKUserAccountPage extends VKInnerPage {
    private static final By mailBoxLoc = By.xpath("//div[starts-with(@class,\"mail_box_cont\")]");
    private static final Integer TIME_WAIT_POPUP = 1;
    private Button btnWriteMsg = new Button(By.xpath("//div[@id=\"profile_message_send\"]/div/a[contains(@href,\"write\")]/button"), "write msg button");
    private Button bthGoHistory = new Button(By.cssSelector("a.mail_box_header_link"), "link to history");
    private WriteMessagePopup messagePopup;

    private Label lblSent = new Label(By.cssSelector("div.top_result_header"), "sent result");

    public VKUserAccountPage() {
        super(By.cssSelector("img.page_avatar_img"), "VK Account Page");
    }


    public void openMsgForm() {
        btnWriteMsg.click();
    }

    public void goHistory() {
        bthGoHistory.click();
    }

    public Message sendMessageInPopup() {
        messagePopup = new WriteMessagePopup(mailBoxLoc, "msg box");
        return messagePopup.sendMessage();
    }

    public void assertPopupSent() {
        assert (lblSent.isVisible());

    }

}
