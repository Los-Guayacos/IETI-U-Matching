package guayacos.chat.components;
import java.io.Serializable;

public class Room implements Serializable {

    private String id;
    private String receiver;
    private String transmitter;

    public Room() {
    }

    public Room(String id, String receiver, String transmitter) {
        this.id = id;
        this.receiver = receiver;
        this.transmitter = transmitter;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTransmitter() {
        return this.transmitter;
    }

    public void setTransmitter(String transmitter) {
        this.transmitter = transmitter;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", receiver='" + getReceiver() + "'" + ", transmitter='"
                + getTransmitter() + "'" + "}";
    }

}

