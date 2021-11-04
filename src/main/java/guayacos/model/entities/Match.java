package guayacos.model.entities;

import java.io.Serializable;
import java.util.Date;

public class Match implements Serializable{

    private String userId;
    private Date matchDate;
    private String picture;
    private String fullName;

    public Match() {
    }

    public Match(String userId, Date matchDate, String picture, String fullName) {
        this.userId = userId;
        this.matchDate = matchDate;
        this.picture = picture;
        this.fullName = fullName;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getMatchDate() {
        return this.matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "{" +
                " userId='" + getUserId() + "'" +
                ", matchDate='" + getMatchDate() + "'" +
                ", picture='" + getPicture() + "'" +
                ", fullName='" + getFullName() + "'" +
                "}";
    }

}
