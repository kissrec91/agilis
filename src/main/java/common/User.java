package common;

/**
 * Created by Rebeka on 2015. 10. 22..
 */
public class User {
    public static enum Sex {
        FEMALE,
        MALE;
    }
    private String name;
    private int height;
    private int weight;
    private Sex sex;

    public User(String name, int height, int weight, Sex sex) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public Sex getSex() {
        return sex;
    }
}
