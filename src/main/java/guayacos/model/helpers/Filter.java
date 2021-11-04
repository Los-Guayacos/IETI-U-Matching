package guayacos.model.helpers;

public class Filter {
    private int age = 99;
    private boolean interests = false;
    private String gender = "Hombre";
    private String college = "Escuela Colombiana de Ingeniería Julio Garavito";
    private String program = "";
    private double rating = 5;


    public Filter() {
    }

    public Filter(int age, boolean interests, String gender, String college, String program, double rating) {
        this.age = age;
        this.interests = interests;
        this.gender = gender;
        this.college = college;
        this.program = program;
        this.rating = rating;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isInterests() {
        return this.interests;
    }

    public boolean getInterests() {
        return this.interests;
    }

    public void setInterests(boolean interests) {
        this.interests = interests;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{" +
                " age='" + getAge() + "'" +
                ", interests='" + isInterests() + "'" +
                ", gender='" + getGender() + "'" +
                ", college='" + getCollege() + "'" +
                ", program='" + getProgram() + "'" +
                ", rating='" + getRating() + "'" +
                "}";
    }

}
