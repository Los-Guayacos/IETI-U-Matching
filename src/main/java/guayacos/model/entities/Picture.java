package guayacos.model.entities;

import java.io.Serializable;

public class Picture implements Serializable{

    private String url;
    private String name;


    public Picture(){

    }

    public Picture(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
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
                " url='" + getUrl() + "'" +
                ", name='" + getName() + "'" +
                "}";
    }

}