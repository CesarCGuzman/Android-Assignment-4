package edu.uncc.assignment04;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String age_value;
    int mood_image_resource;

    public User(String name, String age_value, int mood_image_resource) {
        this.name = name;
        this.age_value = age_value;
        this.mood_image_resource = mood_image_resource;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge_value() {
        return age_value;
    }

    public void setMood_value(String mood_value) {
        this.age_value = mood_value;
    }

    public int getMood_image_resource() {
        return mood_image_resource;
    }

    public void setMood_image_resource(int mood_image_resource) {
        this.mood_image_resource = mood_image_resource;
    }

    @Override
    public String toString() {
        return name;
    }
}
