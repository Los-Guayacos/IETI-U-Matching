package guayacos.model.entities;

import java.io.Serializable;

public class Interest implements Serializable{

    private String name;


    public Interest() {

    }

    public Interest(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                "}";
    }

}