package vk;

import java.time.LocalDateTime;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/9/2017.
 */
public class Message {
    private String msgText;
    private UserAccount sender;
    private UserAccount recipient;
    private LocalDateTime date;

    public Message(String msgText, UserAccount sender, UserAccount recipient, LocalDateTime date) {
        this.msgText = msgText;
        this.sender = sender;
        this.recipient = recipient;
        this.date = date;
    }

    public Message(String msgText, LocalDateTime date) {
        this.msgText = msgText;
        this.date = date;
    }

    public String getMsgText() {
        return msgText.trim();
    }


    public UserAccount getSender() {
        return sender;
    }

    public void setSender(UserAccount sender) {
        this.sender = sender;
    }

    public UserAccount getRecipient() {
        return recipient;
    }

    public void setRecipient(UserAccount recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getDate() {
        return date;
    }

}
