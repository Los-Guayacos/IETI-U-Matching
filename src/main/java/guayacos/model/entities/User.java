package guayacos.model.entities;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class User implements Serializable {

    // Basic info
    private String uid;
    private String fullName;
    private String email;
    private String college;
    private String program;
    private String description;
    private String gender;
    private int semester;
    private int age;

    // Profile info
    private List<Picture> pictures;
    private List<Interest> interests;
    private Date registrationDate;
    private boolean active;
    private boolean verified;

    // Rating
    private float rating;
    private int totalRatings;
    private Map<String, Integer> ratings;

    // Social
    private String instagram;
    private String whatsapp;

    // Matching
    private List<String> likes;
    private List<String> receivedLikes;
    private List<Match> matches;

    // Chat
    private Map<String, String> rooms;

    public User() {

    }

    public User(String uid, String fullName, String email, String college, String program, String description,
                String gender, int semester, int age, List<Picture> pictures, List<Interest> interests,
                Date registrationDate, boolean active, boolean verified, float rating, int totalRatings, Map<String, Integer> ratings, String instagram,
                String whatsapp, List<String> likes, List<String> receivedLikes, List<Match> matches) {
        this.uid = uid;
        this.fullName = fullName;
        this.email = email;
        this.college = college;
        this.program = program;
        this.description = description;
        this.gender = gender;
        this.semester = semester;
        this.age = age;
        this.pictures = pictures;
        this.interests = interests;
        this.registrationDate = registrationDate;
        this.active = active;
        this.verified = verified;
        this.rating = rating;
        this.totalRatings = totalRatings;
        this.ratings = ratings;
        this.instagram = instagram;
        this.whatsapp = whatsapp;
        this.likes = likes;
        this.receivedLikes = receivedLikes;
        this.matches = matches;
    }

    public void addNewRoom(String user, String id) {
        if (rooms == null) {
            rooms = new HashMap<String, String>();
        }
        if (rooms.get(user) == null)
            rooms.put(user, id);
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return this.college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProgram() {
        return this.program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSemester() {
        return this.semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Picture> getPictures() {
        return this.pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Interest> getInterests() {
        return this.interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getVerified() {
        return this.verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalRatings() {
        return this.totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public Map<String, Integer> getRatings(){
        return this.ratings;
    }

    public void setRatings(Map<String, Integer> ratings){
        this.ratings = ratings;
    }

    public String getInstagram() {
        return this.instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getWhatsapp() {
        return this.whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public List<String> getLikes() {
        return this.likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getReceivedLikes() {
        return this.receivedLikes;
    }

    public void setReceivedLikes(List<String> receivedLikes) {
        this.receivedLikes = receivedLikes;
    }

    public List<Match> getMatches() {
        return this.matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Map<String, String> getRooms() {
        return this.rooms;
    }

    public void setRooms(Map<String, String> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(uid, user.uid) && Objects.equals(fullName, user.fullName)
                && Objects.equals(email, user.email) && Objects.equals(college, user.college)
                && Objects.equals(program, user.program) && Objects.equals(description, user.description)
                && Objects.equals(gender, user.gender) && semester == user.semester && age == user.age
                && Objects.equals(pictures, user.pictures) && Objects.equals(interests, user.interests)
                && Objects.equals(registrationDate, user.registrationDate) && active == user.active
                && verified == user.verified && rating == user.rating && totalRatings == user.totalRatings
                && Objects.equals(instagram, user.instagram) && Objects.equals(whatsapp, user.whatsapp)
                && Objects.equals(likes, user.likes) && Objects.equals(matches, user.matches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, fullName, email, college, program, description, gender, semester, age, pictures,
                interests, registrationDate, active, verified, rating, totalRatings, instagram, whatsapp, likes,
                matches);
    }

    @Override
    public String toString() {
        return "{" + " uid='" + getUid() + "'" + ", fullName='" + getFullName() + "'" + ", email='" + getEmail() + "'"
                + ", college='" + getCollege() + "'" + ", program='" + getProgram() + "'" + ", description='"
                + getDescription() + "'" + ", gender='" + getGender() + "'" + ", semester='" + getSemester() + "'"
                + ", age='" + getAge() + "'" + ", pictures='" + getPictures() + "'" + ", interests='" + getInterests()
                + "'" + ", registrationDate='" + getRegistrationDate() + "'" + ", active='" + getActive() + "'"
                + ", verified='" + getVerified() + "'" + ", rating='" + getRating() + "'" + ", totalRatings='"
                + getTotalRatings() + "'" + ", instagram='" + getInstagram() + "'" + ", whatsapp='" + getWhatsapp()
                + "'" + ", likes='" + getLikes() + "'" + ", matches='" + getMatches() + "'" + "}";
    }

}
