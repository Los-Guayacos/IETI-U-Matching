package guayacos.model.entities;

public class Message {

    private String authorId;
    private String authorName;
    private String receiver;
    private String receiverName;
    private String date;
    private String content;


    public Message() {
    }

    public Message(String authorId, String authorName, String receiver, String receiverName, String date, String content) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.receiver = receiver;
        this.receiverName = receiverName;
        this.date = date;
        this.content = content;
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getreceiverName() {
        return this.receiverName;
    }

    public void setreceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{" +
                " authorId='" + getAuthorId() + "'" +
                ", authorName='" + getAuthorName() + "'" +
                ", receiver='" + getReceiver() + "'" +
                ", receiverName='" + getreceiverName() + "'" +
                ", date='" + getDate() + "'" +
                ", content='" + getContent() + "'" +
                "}";
    }

}

