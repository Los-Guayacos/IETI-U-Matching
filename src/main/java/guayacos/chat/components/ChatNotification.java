package guayacos.chat.components;

public class ChatNotification {

    private String senderId;
    private String senderName;


    public ChatNotification() {
    }

    public ChatNotification(String senderId, String senderName) {
        this.senderId = senderId;
        this.senderName = senderName;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public String toString() {
        return "{" +
                " senderId='" + getSenderId() + "'" +
                ", senderName='" + getSenderName() + "'" +
                "}";
    }

}
